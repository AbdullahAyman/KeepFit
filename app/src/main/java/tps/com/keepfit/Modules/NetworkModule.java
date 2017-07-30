package tps.com.keepfit.Modules;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;
import tps.com.keepfit.Interfaces.Qualifier.ApplicationContext;
import tps.com.keepfit.Interfaces.Scop.KeepFitScope;

/**
 * Created by aayman on 5/25/2017.
 */
@Module(includes = ContextModule.class)
public class NetworkModule {
    @Provides
    @KeepFitScope

    public Cache cache(File cachFile) {
        return new Cache(cachFile, 10 * 1000 * 1000);
    }

    @Provides
    @KeepFitScope

    public File cachFile(@ApplicationContext Context context) {
        return new File(context.getCacheDir(), "okhttp-cache");
    }

    @Provides
    @KeepFitScope

    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return loggingInterceptor;
    }

    @Provides
    @KeepFitScope

    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }
}
