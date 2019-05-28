package com.example.generator;

import com.example.data.ConfigData;
import com.example.model.ConfigModel;
import com.example.util.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 在这里编写说明
 *
 * @author: CL
 * @email: caolu@sunseaaiot.com
 * @date: 2019-03-01 14:41:00
 */
public class ServiceImplGenerator {
    public static void generate() {
        System.out.println("开始生成serviceImpl");


        String projectPath = System.getProperty("user.dir");

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        //模板文件所在的classpath的路径
        Template t = ve.getTemplate("/templates/serviceImpl.java.vm");
        //数据
        ConfigModel configModel = ConfigData.getConfigModel();
        VelocityContext ctx = new VelocityContext();
        ctx.put("config", configModel);

        String outPath = projectPath + "/src/main/java/"
                + configModel.getBasePackage().replace(".", "/")
                + "/"
                + configModel.getModulePackage().replace(".", "/")
                + "/service/impl/"
                + configModel.getEntityName()
                + "ServiceImpl.java";
        File file = new File(outPath);
        if (file.exists()) {
            return;
        }
        try (FileWriter fw = new FileWriter(FileUtils.createFile(outPath))) {
            t.merge(ctx, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("生成serviceImpl完成");

    }
}
