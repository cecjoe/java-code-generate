package com.joe.code.common.enums;

import com.joe.code.common.utils.PageResultObject;
import com.joe.code.common.utils.ResultObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public enum ResultStatusEnum {

    SUCCESS(0, "请求成功"), FAIL(1, "请求失败");

    private Integer index;
    private String name;

    public <T> ResultObject<T> ok(T t){
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(index);
        resultObject.setMsg(name);
        resultObject.setData(t);
        return resultObject;
    }

    public ResultObject ok(){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(index);
        resultObject.setMsg(name);
        return resultObject;
    }

    public <M> PageResultObject<M> ok(M t,long total){
        PageResultObject<M> pageResultObject = new PageResultObject<>();
        pageResultObject.setCount(total);
        pageResultObject.setCode(index);
        pageResultObject.setMsg(name);
        pageResultObject.setData(t);
        return pageResultObject;
    }

    public ResultObject backMsg(String message){
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(index);
        if(!StringUtils.isEmpty(message)){
            resultObject.setMsg(message);
        }else{
            resultObject.setMsg(name);
        }

        return resultObject;
    }
}
