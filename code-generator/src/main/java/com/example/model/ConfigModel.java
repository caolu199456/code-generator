package com.example.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在这里编写说明
 *
 * @author: CL
 * @email: caolu@sunseaaiot.com
 * @date: 2019-03-01 14:45:00
 */
@Data
public class ConfigModel {
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;


    private String author;
    private String email;


    private String basePackage;
    private String modulePackage;
    private String entityName;
    private String tableName;


    private String date;
    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 映射名 UserInfo 转为 userInfo
     */
    private String entityVarName;

    /**
     * 下划线名称 UserInfo转user_info
     */
    private String entityLineName;

    public String getEntityVarName() {
        if (entityName.length() > 1) {
            return (""+entityName.charAt(0)).toLowerCase() + entityName.substring(1);
        }else {
            return (entityName.charAt(0) + "").toLowerCase();
        }
    }
    public String getEntityLineName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entityName.length(); i++) {
            char c = entityName.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                //大写字母
                sb.append("_").append((c + "").toLowerCase());
            } else {
                sb.append((c + "").toLowerCase());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ConfigModel configModel = new ConfigModel();
        configModel.setEntityName("UserInfo");
        System.out.println(configModel.getEntityLineName());
    }
}
