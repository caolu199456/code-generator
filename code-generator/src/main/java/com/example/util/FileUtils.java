package com.example.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * 在这里编写说明
 *
 * @author: CL
 * @email: caolu@sunseaaiot.com
 * @date: 2019-03-01 15:15:00
 */
public class FileUtils {
    public static File createFile(String outPath) {
        if (StringUtils.isNotEmpty(outPath)) {
            File file = new File(outPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;
        }
        return null;
    }
}
