package com.evgkit.simpleandroidapp.network;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    public static final String API_HOST = "https://api.giphy.com/v1/gifs/";
    public static final String API_KEY = "AXTLFVr9akCccU9lMuHcs6SGKkHkJgtM";

    private static final long HTTP_CACHE_SIZE = 1024 * 1024 * 24L; // 24 MB

    private final Api api;

    public NetworkModule(Context context) {
        final Gson gson = provideGson();
        final Interceptor interceptor = provideRequestInterceptor();
        final OkHttpClient okHttpClient = provideOkHttp(context.getCacheDir(), interceptor);
        final Retrofit retrofit = provideRetrofit(okHttpClient, gson);
        api = provideApi(retrofit);

        providePicasso(context, okHttpClient);
    }

    public Api getApi() {
        return api;
    }

    private OkHttpClient provideOkHttp(File cacheDir, Interceptor interceptor) {
        final HttpLoggingInterceptor.Logger logger = new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                Log.d("OkHttp", message);
            }
        };

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger) //
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);

        return new OkHttpClient.Builder() //
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .cache(new Cache(new File(cacheDir, "http"), HTTP_CACHE_SIZE))
                .build();
    }

    private Gson provideGson() {
        return new GsonBuilder().create();
    }

    private Retrofit provideRetrofit(OkHttpClient okHttp, Gson gson) {
        return new Retrofit.Builder() //
                .baseUrl(API_HOST)
                .addConverterFactory(new EnvelopingConverter())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp)
                .build();
    }

    private Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    private Interceptor provideRequestInterceptor() {
        return new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                final Request originalRequest = chain.request();
                final HttpUrl newUrl = originalRequest.url().newBuilder() //
                        .addQueryParameter("api_key", API_KEY)
                        .build();

                final Request newRequest = originalRequest.newBuilder() //
                        .url(newUrl)
                        .addHeader("Accept", "application/json")
                        .build();

                return chain.proceed(newRequest);
            }
        };
    }

    private Picasso providePicasso(Context context, OkHttpClient httpClient) {
        final Picasso picasso = new Picasso.Builder(context) //
                //.indicatorsEnabled(BuildConfig.DEBUG)
                .downloader(new OkHttp3Downloader(httpClient))
                .build();

        // Setup global Picasso instance.
        Picasso.setSingletonInstance(picasso);
        return picasso;
    }
}
