package com.clj.panda.web;

import com.alibaba.fastjson.JSON;
import com.clj.panda.common.resp.JsonpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wana on 2015/10/13.
 */
public class FastJsonHttpMessageConverter extends com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter {
    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (obj instanceof JsonpResult) {
            JsonpResult jsonp = (JsonpResult) obj;
            if (StringUtils.isNotEmpty(jsonp.getMethod())) {
                PrintWriter pw = new PrintWriter(outputMessage.getBody());
                pw.write(jsonp.getMethod() + "(" + JSON.toJSONString(jsonp.getResult(),getFeatures()) + ")");
                pw.flush();
            }else{
                super.writeInternal(obj, outputMessage);
            }
        } else {
            super.writeInternal(obj, outputMessage);
        }
    }
}
