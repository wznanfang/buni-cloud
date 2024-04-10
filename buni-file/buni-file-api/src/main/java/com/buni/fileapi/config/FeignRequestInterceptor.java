package com.buni.fileapi.config;

import cn.hutool.core.util.RandomUtil;
import com.buni.buniframework.constant.CommonConstant;
import com.buni.buniframework.util.HeaderUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        System.out.println(HeaderUtil.getToken());
        template.header(CommonConstant.AUTHORIZATION, CommonConstant.PREFIX + HeaderUtil.getToken());
        template.header(CommonConstant.GATEWAY_KEY, RandomUtil.randomString(32));
        System.err.println(template);
    }


}
