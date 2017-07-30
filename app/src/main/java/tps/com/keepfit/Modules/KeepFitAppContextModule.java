package tps.com.keepfit.Modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tps.com.keepfit.Interfaces.Qualifier.ApplicationContext;
import tps.com.keepfit.Interfaces.Scop.KeepFitScope;

/**
 * Created by aayman on 7/30/2017.
 */

@Module(includes = ContextModule.class)
public class KeepFitAppContextModule {

    @Provides
    @KeepFitScope
    public Context getKeepFitContext(@ApplicationContext Context context) {
        return context;
    }
}