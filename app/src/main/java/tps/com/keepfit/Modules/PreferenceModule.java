package tps.com.keepfit.Modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import tps.com.keepfit.Interfaces.Qualifier.ApplicationContext;
import tps.com.keepfit.Interfaces.Scop.KeepFitScope;

/**
 * Created by aayman on 5/25/2017.
 */
@Module(includes = ContextModule.class)
public class PreferenceModule {

    @Provides
    @KeepFitScope
    public SharedPreferences getSharedPreference(@ApplicationContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
