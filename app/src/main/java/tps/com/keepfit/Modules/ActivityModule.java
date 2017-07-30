package tps.com.keepfit.Modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import tps.com.keepfit.Interfaces.Qualifier.ApplicationContext;
import tps.com.keepfit.Interfaces.Scop.KeepFitScope;

/**
 * Created by aayman on 7/30/2017.
 */

@Module
public class ActivityModule {
    private Activity mContext;

    public ActivityModule(@ApplicationContext Activity context) {
        this.mContext = context;

    }

    @Provides
    @KeepFitScope
   /* @Named("activity_context")*/
    public Activity ActivityContext() {
        return mContext;
    }
}
