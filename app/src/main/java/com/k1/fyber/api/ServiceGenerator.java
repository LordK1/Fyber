package com.k1.fyber.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * to generate {@link ApiService} instance by {@link Retrofit#create(Class)} and usage in whole project
 * // TODO: 8/28/16 you can use Dagger 2 as dependency injection for this class
 * Created by K1 on 8/28/16.
 */
public final class ServiceGenerator {

    private static final String BASE_URL = "http://api.fyber.com/feed/v1/";
    private static final String TAG = ServiceGenerator.class.getSimpleName();
    private static final Gson gson = new GsonBuilder().create();
    private static final Retrofit.Builder retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));

    /**
     * Create service for using in other class
     * Accept-Encoding: gzip
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
        final OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new LoggerInterceptor()).build();
        return retrofit.client(build).build().create(serviceClass);
    }


    /**
     * Just for calculate nano time of request sending and response receiving
     * and implementation of {@link Interceptor}
     */
    private static class LoggerInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final long t1 = System.nanoTime();
            final Request original = chain.request();
            final Request.Builder requestBuilder = original.newBuilder();
            final Request request = requestBuilder
                    .url(original.url())
                    .method(original.method(), original.body()).build();

            Log.d(TAG, String.format("Sending Request %s on %s%n%s ",
                    request.url(), chain.connection(), request.headers()));
            final Response response = chain.proceed(requestBuilder.build());
            long t2 = System.nanoTime();
            Log.d(TAG, String.format("Received Response for %s in %.1fms %n %s",
                    response.request().url(), (t2 - t1) / 1e6d, response.body()
            ));
            return response;
        }
    }
}
