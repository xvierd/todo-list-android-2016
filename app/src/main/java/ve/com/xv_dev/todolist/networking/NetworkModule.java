package ve.com.xv_dev.todolist.networking;



import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ve.com.xv_dev.todolist.BuildConfig;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */
@Module
public class NetworkModule {
    File cacheFile;

    CookieStore cookieStore;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .cache(cache)
                .cookieJar(new CookieStore())
                .connectTimeout(20, TimeUnit.SECONDS) //TODO time in build.gradle
                .readTimeout(20, TimeUnit.SECONDS)
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();
                        original.headers();
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("authToken", "ummm"); // <-- this is the important line
                        Request request = requestBuilder.build();
                        okhttp3.Response response = chain.proceed(request);
                        String aaa = response.networkResponse().header("authToken");
                        Headers allHeaders = response.headers();
                        String headerValue[] = allHeaders.get("Set-Cookie").split("=", 2);
                        requestBuilder.addHeader("authToken", headerValue[1]);
                        return chain.proceed(request);
                    }
                })*/
                .build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public Service providesService(
            NetworkService networkService) {
        return new Service(networkService);
    }

}
