package com.sisp.common.utils;

import com.sisp.bean.HttpResponseEntity;
import java.util.List;


public class ResponseGenerator {
    private ResponseGenerator(){}
    public static synchronized HttpResponseEntity respondPost(String goodCode, String badCode, String goodMessage, String badMessage, int result){
        HttpResponseEntity response = new HttpResponseEntity();
        if(result!=0) {
            response.setCode(goodCode);
            response.setData(result);
            response.setMessage(goodMessage);
        } else {
            response.setCode(badCode);
            response.setData(0);
            response.setMessage(badMessage);
        }
        return response;
    }

    public static synchronized HttpResponseEntity respondGetList(String goodCode, String badCode, String goodMessage, String badMessage, List result){
        HttpResponseEntity response = new HttpResponseEntity();
        if(!result.isEmpty()) {
            response.setCode(goodCode);
            response.setData(result);
            response.setMessage(goodMessage);
        } else {
            response.setCode(badCode);
            response.setData(0);
            response.setMessage(badMessage);
        }
        return response;
    }
}
