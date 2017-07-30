package tps.com.keepfit.Interfaces.Component;

import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;

import dagger.Component;
import tps.com.keepfit.Interfaces.Scop.KeepFitScope;
import tps.com.keepfit.Modules.ActivityModule;
import tps.com.keepfit.Modules.ContextModule;
import tps.com.keepfit.Modules.KeepFitAppContextModule;
import tps.com.keepfit.Modules.NetworkModule;
import tps.com.keepfit.Modules.PicassoModule;
import tps.com.keepfit.Modules.PreferenceModule;
import tps.com.keepfit.Modules.RetrofitModule;
import tps.com.keepfit.Networking.Interfaces.RetrofitInterface;

/**
 * Created by aayman on 7/30/2017.
 */

@Component(modules = {ContextModule.class, NetworkModule.class, ActivityModule.class,
        PicassoModule.class, RetrofitModule.class, PreferenceModule.class, KeepFitAppContextModule.class})
@KeepFitScope
public interface KeepFitComponent {

    Picasso getPicasso();

    RetrofitInterface getRetrofitInterface();

    SharedPreferences getSharedPreferences();


    Context getKeepFitContext();
}