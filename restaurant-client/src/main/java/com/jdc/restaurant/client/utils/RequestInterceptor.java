package com.jdc.restaurant.client.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor{

	@Override
	public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newReq = null;

        try {

            Request.Builder builder = request.newBuilder();

            String token = SessionContext.getToken();
            
            if(null != token) {
                builder = builder.addHeader("Authorization", token);
            }

            newReq = builder.build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chain.proceed(null != newReq ? newReq : request);
	}

}
