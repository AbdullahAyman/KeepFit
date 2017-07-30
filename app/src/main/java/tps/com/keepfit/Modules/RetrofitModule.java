package tps.com.keepfit.Modules;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tps.com.keepfit.Interfaces.Scop.KeepFitScope;
import tps.com.keepfit.Networking.Interfaces.RetrofitInterface;

/**
 * Created by aayman on 5/25/2017.
 */
@Module(includes = NetworkModule.class)
public class RetrofitModule {

    @Provides
    @KeepFitScope
    public Retrofit getRetrofit(OkHttpClient okHttpClient, String baseurl) {
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @KeepFitScope
    public RetrofitInterface getRetrofitInterface(Retrofit getRetrofit) {

        return getRetrofit.create(RetrofitInterface.class);
    }

}
