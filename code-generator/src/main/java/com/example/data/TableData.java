package com.example.data;


import com.alibaba.fastjson.JSON;
import com.example.model.ConfigModel;
import com.example.model.TableInfo;
import com.example.util.JDBCUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @author: CL
 * @email: caolu@sunseaaiot.com
 * @date: 2019-03-01 14:44:00
 */
public class TableData {
    public static TableInfo getTableInfo() {
        TableInfo tableInfo = new TableInfo();

        ConfigModel configModel = ConfigData.getConfigModel();
        JDBCUtils.config(configModel.getDbUrl(), "com.mysql.jdbc.Driver", configModel.getDbUsername(), configModel.getDbPassword());

        String dbName = configModel.getDbUrl().substring(configModel.getDbUrl().lastIndexOf("/")+1, configModel.getDbUrl().indexOf("?"));

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            //表相关
            List<Map<String, Object>> tableList = JDBCUtils.find(connection, "select * from information_schema.tables where  table_name =? and table_schema=?", new String[]{configModel.getTableName(),dbName});
            tableInfo.setTableName(configModel.getTableName());
            tableInfo.setTableComment((String) tableList.get(0).get("TABLE_COMMENT"));


            List<TableInfo.FieldInfo> fieldInfoList = new ArrayList<>();
            Set<String> importClassSet = new HashSet<>();
            List<Map<String, Object>> fieldList = JDBCUtils.find(connection, "select * from information_schema.columns where   table_name = ? and table_schema=?", new String[]{configModel.getTableName(),dbName});
            for (Map<String, Object> fieldMap : fieldList) {
                TableInfo.FieldInfo fieldInfo = new TableInfo.FieldInfo();

                fieldInfo.setFieldName((String) fieldMap.get("COLUMN_NAME"));

                String columnKey = (String) fieldMap.get("COLUMN_KEY");
                if (StringUtils.isNotEmpty(columnKey) && "PRI".equalsIgnoreCase(columnKey)) {
                    fieldInfo.setPrimary(true);
                } else {
                    fieldInfo.setPrimary(false);
                }

                fieldInfo.setFieldType((String) fieldMap.get("DATA_TYPE"));

                fieldInfo.setFieldComment((String) fieldMap.get("COLUMN_COMMENT"));

                fieldInfoList.add(fieldInfo);


                if ("String".equals(fieldInfo.getJavaType())) {
                    //将要导入的包
                    importClassSet.add("java.lang.String");
                } else if ("Byte".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.lang.Byte");
                } else if ("Short".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.lang.Short");
                }else if ("Integer".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.lang.Integer");
                } else if ("Long".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.lang.Long");
                }  else if ("Float".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.lang.Float");
                }  else if ("Double".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.lang.Double");
                }  else if ("BigDecimal".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.math.BigDecimal");
                } else if ("Date".equals(fieldInfo.getJavaType())) {
                    importClassSet.add("java.util.Date");
                }

            }
            tableInfo.setFieldInfoList(fieldInfoList);
            tableInfo.setImportClassSet(importClassSet);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.closeConn(connection, null, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return tableInfo;
    }

    public static void main(String[] args) {
        TableInfo tableInfo = getTableInfo();
        System.out.println(JSON.toJSONString(tableInfo));
    }
}
