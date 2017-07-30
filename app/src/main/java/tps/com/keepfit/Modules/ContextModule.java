package tps.com.keepfit.Modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tps.com.keepfit.Interfaces.Qualifier.ApplicationContext;
import tps.com.keepfit.Interfaces.Scop.KeepFitScope;

/**
 * Created by aayman on 5/25/2017.
 */
@Module
public class ContextModule {
    private Context context;
    private String baseURL;

    public ContextModule(Context contxt, String baseurl) {
        this.context = contxt.getApplicationContext();
        this.baseURL = baseurl;
    }

    @Provides
    @KeepFitScope
    @ApplicationContext
    public Context context() {
        return context;
    }

    @Provides
    @KeepFitScope
    public String baseurl() {
        return baseURL;
    }
}
