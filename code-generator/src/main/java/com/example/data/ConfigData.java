package com.example.data;

import com.example.model.ConfigModel;

import java.io.IOException;
import java.util.Properties;

/**
 * 得到gen.properties的配置文件
 *
 * @author: CL
 * @email: caolu@sunseaaiot.com
 * @date: 2019-03-01 14:44:00
 */
public class ConfigData {
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(ConfigData.class.getResourceAsStream("/gen.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ConfigModel getConfigModel() {
        ConfigModel configModel = new ConfigModel();
        configModel.setDbUrl(properties.getProperty("db.url"));
        configModel.setDbUsername(properties.getProperty("db.username"));
        configModel.setDbPassword(properties.getProperty("db.password"));

        configModel.setAuthor(properties.getProperty("author"));
        configModel.setEmail(properties.getProperty("email"));

        configModel.setBasePackage(properties.getProperty("basePackage"));
        configModel.setModulePackage(properties.getProperty("modulePackage"));
        configModel.setEntityName(properties.getProperty("entityName"));
        configModel.setTableName(properties.getProperty("tableName"));

        return configModel;
    }
}
