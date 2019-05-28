package com.example;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    protected HttpServletRequest httpServletRequest;



    /**
     * 填充保存时的公共参数
     * @param obj
     */
    protected void fillSaveCommonData(Object obj) {
    }
    /**
     * 填充编辑时的公共参数
     * @param obj
     */
    protected void fillEditCommonData(Object obj) {
    }
}
