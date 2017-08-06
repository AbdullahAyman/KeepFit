package tps.com.keepfit.Presenters;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Response;
import tps.com.keepfit.DataModel.GoogleGYMPlaces.NearestGymPlaces;
import tps.com.keepfit.Interactors.NearestPlacesInteractor;
import tps.com.keepfit.Interfaces.INearestPlacesInteractor;
import tps.com.keepfit.Interfaces.INearestPlacesPresenter;
import tps.com.keepfit.Interfaces.INearestPlacesView;
import tps.com.keepfit.Networking.Interfaces.CallBackJSONObject;

/**
 * Created by aayman on 8/2/2017.
 */

public class NearestPlacesPresenter implements INearestPlacesPresenter, CallBackJSONObject {

    INearestPlacesView mNearestPlacesView;
    INearestPlacesInteractor iNearestPlacesInteractor;

    public NearestPlacesPresenter(INearestPlacesView iNearestPlacesView) {
        mNearestPlacesView = iNearestPlacesView;
        iNearestPlacesInteractor = new NearestPlacesInteractor(this);
    }

    @Override
    public void retrieveNearestPlaces() {
        iNearestPlacesInteractor.LoadNearestPlaces(1000);
    }

    @Override
    public void onSuccess(Response<JsonObject> o) {
        Gson gson = new Gson();
        Type listType = new TypeToken<NearestGymPlaces>() {
        }.getType();
        try {
            NearestGymPlaces mResponse = gson.fromJson(o.body(), listType);
            mNearestPlacesView.assignDataToMap(mResponse);

        } catch (Exception e) {
            mNearestPlacesView.assignDataToMap(null);
        }

    }

    @Override
    public void OnFail(Throwable o) {

    }
}
