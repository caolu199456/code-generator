package com.example.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 对应表的描述
 *
 * @author: CL
 * @email: caolu@sunseaaiot.com
 * @date: 2019-03-01 15:40:00
 */
@Data
public class TableInfo {
    private String tableName;
    /**
     * 表的注释
     */
    private String tableComment;
    /**
     * 表的字段信息
     */
    private List<FieldInfo> fieldInfoList;

    /**
     * 需要导入的类
     */
    private Set<String> importClassSet;

    @Data
    public static class FieldInfo {
        private String fieldName;
        /**
         * 是否主键
         */
        private boolean primary;
        /**
         * 数据库字段类型
         */
        private String fieldType;
        /**
         * 字段注释
         */
        private String fieldComment;
        /**
         * java的类型
         */
        private String javaType;
        /**
         * java名字驼峰
         */
        private String javaFieldName;


        public String getJavaType() {
            if (fieldType.equals("varchar")) {
                return "String";
            }else if (fieldType.equals("text")) {
                return "String";
            }else if (fieldType.equals("tinyint")) {
                return "Byte";
            }else if (fieldType.equals("mediumint")) {
                return "Short";
            }else if (fieldType.equals("int")) {
                return "Integer";
            }else if (fieldType.equals("bigint")) {
                return "Long";
            }else if (fieldType.equals("float")) {
                return "Float";
            }else if (fieldType.equals("double")) {
                return "Double";
            }else if (fieldType.equals("decimal")) {
                return "BigDecimal";
            }else if (fieldType.equals("datetime")) {
                return "Date";
            }
            return "String";
        }

        public String getJavaFieldName() {
            //驼峰转换
            if (fieldName==null||"".equals(fieldName.trim())){
                return "";
            }
            int len=fieldName.length();
            StringBuilder sb=new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                char c=fieldName.charAt(i);
                if (c=='_'){
                    if (++i<len){
                        sb.append(Character.toUpperCase(fieldName.charAt(i)));
                    }
                }else{
                    sb.append(c);
                }
            }
            return sb.toString();
        }

    }

    public static void main(String[] args) {
    }
}
