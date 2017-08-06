package tps.com.keepfit.utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;
import tps.com.keepfit.DataModel.CardsDataModel;
import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.Interfaces.Component.DaggerKeepFitComponent;
import tps.com.keepfit.Interfaces.Component.KeepFitComponent;
import tps.com.keepfit.Interfaces.IMainActivityPresenter;
import tps.com.keepfit.Interfaces.IMainActivityViews;
import tps.com.keepfit.Modules.ContextModule;
import tps.com.keepfit.Networking.Interfaces.RetrofitInterface;
import tps.com.keepfit.Presenters.MainActivityPresenter;
import tps.com.keepfit.R;


public class KeepFitApp extends Application implements IMainActivityViews {
    public static List<CardsDataModel_> cardListOfData = null;
    private static String BASE_URL = "";
    private static RetrofitInterface retrofitInterface;
    private static KeepFitComponent keepFitComponent;
    private static SharedPreferences sharedPreferences;
    private static Picasso picasso;
    private static Context mContext;
    IMainActivityPresenter iMainActivityViews;

    public static KeepFitApp newInstance() {
        return new KeepFitApp();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BASE_URL = getResources().getString(R.string.base);
        Timber.plant(new Timber.DebugTree());
        keepFitComponent = DaggerKeepFitComponent.builder()
                .contextModule(new ContextModule(getApplicationContext(), BASE_URL))//we can include module with constructors only
                /*.networkModule(new NetworkModule())
                .picassoModule(new PicassoModule())   // we cam delete all non Construction modules
                .retrofitModule(new RetrofitModule())
                .preferenceModule(new PreferenceModule())*/
                .build();
        retrofitInterface = keepFitComponent.getRetrofitInterface();
        picasso = keepFitComponent.getPicasso();
        sharedPreferences = keepFitComponent.getSharedPreferences();
        mContext = keepFitComponent.getKeepFitContext();
        if (iMainActivityViews == null) {
            iMainActivityViews = new MainActivityPresenter(this);
            iMainActivityViews.loadExercises();
        }
    }


    /**
     * will create instance of network connection
     */
    public RetrofitInterface newRetrofitRequest() {
        Retrofit retrofit = null;
        RetrofitInterface retrofitInterface = null;

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.youtube.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        return retrofitInterface;
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void displayViewData(CardsDataModel dataModel) {
        if (dataModel.getCardsDataModel() != null)
            cardListOfData = dataModel.getCardsDataModel();
    }
}
