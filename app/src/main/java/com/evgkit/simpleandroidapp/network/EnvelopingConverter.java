package com.evgkit.simpleandroidapp.network;


import android.support.annotation.Nullable;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class EnvelopingConverter extends Converter.Factory {
    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Type envelopedType = TypeToken.getParameterized(Envelope.class, type).getType();

        final Converter<ResponseBody, Envelope<?>> delegate = retrofit.nextResponseBodyConverter(this, envelopedType, annotations);

        return new Converter<ResponseBody, Object>() {
            @Override public Object convert(ResponseBody body) throws IOException {
                return delegate.convert(body).getData();
            }
        };
    }
}
