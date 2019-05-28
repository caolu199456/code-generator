package com.example;

import com.example.generator.*;

/**
 * 在这里编写说明
 *
 * @author: CL
 * @email: caolu@sunseaaiot.com
 * @date: 2019-03-01 14:38:00
 */
public class CodeGenerator {
    public static void main(String[] args) {
        //xml生成
        MapperXmlGenerator.generate();
        //dao生成
        DaoGenerator.generate();
        //entity生成
        EntityGenerator.generate();
        //dto生成
        DtoGenerator.generate();
        //service生成
        ServiceGenerator.generate();
        //serviceImpl生成
        ServiceImplGenerator.generate();
        //controller生成
        ControllerGenerator.generate();
        //html生成
        HtmlGenerator.generate();
        //js生成
        JsGenerator.generate();
    }
}
