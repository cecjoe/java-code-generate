package com.joe.code.common.utils;

import com.joe.code.common.annotations.NotQueryField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class DynamicQueryExecutor extends AbstractDynamicExecutor {

    private DynamicConnection dynamicConnection = null;

    private PreparedStatement statement = null;

    private ResultSet resultSet = null;

    public DynamicQueryExecutor(){}
    public DynamicQueryExecutor(DynamicConnection dynamicConnection){
        this.dynamicConnection = dynamicConnection;
        dynamicConnection.setDynamicExecutor(this);
    }


    public <T, M> List<M> execSQLQuery(String sql, T params, Class<M> clazz) {

        List<String> parameters = StringUtil.getParamsByStr(sql);

        sql = sql.replaceAll("\\{.*?\\}", "?");
        try {
            List<M> list = new ArrayList<M>();
            PreparedStatement preparedStatement = dynamicConnection.getConnection().prepareStatement(sql);
            // 3. 设置查询参数
            if(!CollectionUtils.isEmpty(parameters)){
                if(params == null){
                    throw new IllegalArgumentException("SQL参数异常！");
                }
                for(int i=0; i<parameters.size(); i++){
                    String fieleLabel = StringUtil.getCleanFieleName(parameters.get(i));
                    Field field = ReflectUtil.getFieldByClass(params.getClass(), fieleLabel);
                    if(field == null){
                        throw new IllegalArgumentException("SQL参数错误！ ");
                    }

                    Type genericType = field.getGenericType();
                    field.setAccessible(true);
                    if (genericType.getTypeName().contains("String")){
                        preparedStatement.setString(i+1, field.get(params).toString());
                    }else if(genericType.getTypeName().contains("Integer")){
                        preparedStatement.setInt(i+1, field.getInt(params));
                    }else{}
                }
            }

            resultSet = preparedStatement.executeQuery();
            Field[] fields = clazz.getDeclaredFields();
            while(resultSet.next()){
                M m = clazz.newInstance();
                //Java反射 通过DeclaredFields();
                for(Field field : fields){

                    //配置了不用查询的字段不对其进行赋值
                    if(field.isAnnotationPresent(NotQueryField.class)){
                        continue;
                    }
                    field.setAccessible(true);
                    String value = resultSet.getString(ChangeChar.camelToUnderline(field.getName(), 1));
                    field.set(m, value);

                }
                list.add(m);
            }
            return list;
        }catch (SQLException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            log.error("SQL会话获取异常："+e.getMessage());
        }

        return null;
    }

    @Override
    protected void closeConnection(){

        try {

            dynamicConnection.getConnection().close();

            if(statement != null){
                statement.close();
            }

            if(resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e){
            log.error("数据连接释放资源异常： " + e.getMessage());
        }
    }
}
