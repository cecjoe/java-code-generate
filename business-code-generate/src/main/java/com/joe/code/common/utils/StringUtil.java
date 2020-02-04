package com.joe.code.common.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    public static void main(String[] args) {
//        String str = "select * from order where createdUser = ${currentUser} and  depart = ${currentOrg} and status = 'VALID'";
//        String reg = "\\$\\{[a-zA-Z0-9]+\\}";//定义正则表达式
//
//        Pattern patten = Pattern.compile(reg);//编译正则表达式
//        Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
//
//        List<String> matchStrs = new ArrayList<>();
//
//        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
//            matchStrs.add(matcher.group());//获取当前匹配的值
//        }
//
//        for (int i = 0; i < matchStrs.size(); i++) {
//            System.out.println(matchStrs.get(i));
//        }
    }

    public static List<String> getParamsByStr(String sql){
        String reg = "\\{[a-zA-Z0-9]+\\}";//定义正则表达式

        Pattern patten = Pattern.compile(reg);//编译正则表达式
        Matcher matcher = patten.matcher(sql);// 指定要匹配的字符串

        List<String> matchStrs = new ArrayList<>();

        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
            matchStrs.add(matcher.group());//获取当前匹配的值
        }

        return matchStrs;
    }

    public static String getCleanFieleName(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }

        return str.replaceAll("[\\{\\}]","");
    }


    //替换成空字符串
//
//    String  ss = "[a12,da,das]";
//
//    String replaceAll = ss.replaceAll("[\\[\\]]","");
//
//System.out.println(replaceAll);// a12,da,das
//
//
//
////替换小括号同样原理
//
//    String  ss = "(a12,da,das)";
//    String replaceAll = ss.replaceAll("[\\(\\)]","");
//
//System.out.println(replaceAll);// a12,da,das

}
