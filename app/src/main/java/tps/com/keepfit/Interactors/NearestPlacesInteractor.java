package tps.com.keepfit.Interactors;

import android.content.Context;
import android.location.Location;

import com.google.gson.JsonObject;

import retrofit2.Response;
import tps.com.keepfit.Interfaces.INearestPlacesInteractor;
import tps.com.keepfit.Networking.Interfaces.CallBackJSONObject;
import tps.com.keepfit.Networking.Parser.JSONObjectParser;
import tps.com.keepfit.R;
import tps.com.keepfit.utilities.GPSTracker;
import tps.com.keepfit.utilities.KeepFitApp;

/**
 * Created by aayman on 8/2/2017.
 */

public class NearestPlacesInteractor implements INearestPlacesInteractor, CallBackJSONObject {
    Context mContext;
    CallBackJSONObject mCallBackJSONObject;

    public NearestPlacesInteractor(CallBackJSONObject callBackJSONObject) {
        mContext = KeepFitApp.newInstance().getContext();
        mCallBackJSONObject = callBackJSONObject;
    }

    @Override
    public void onSuccess(Response<JsonObject> o) {
        mCallBackJSONObject.onSuccess(o);
    }

    @Override
    public void OnFail(Throwable o) {
        mCallBackJSONObject.OnFail(o);

    }

    @Override
    public void LoadNearestPlaces(int Radius) {

        GPSTracker mGPSTracker = new GPSTracker(mContext);
        Location location = mGPSTracker.getLocation();
        mGPSTracker.stopUsingGPS();
        if (location != null) {
            String URL = mContext.getResources().getString(R.string.nearestLocation) +
                    location.getLatitude() + "," + location.getLongitude() +
                    mContext.getResources().getString(R.string.radius) +
                    Radius + mContext.getResources().getString(R.string.remainString) +
                    mContext.getResources().getString(R.string.googleKey);
            JSONObjectParser jsonObjectParser = new JSONObjectParser(this);
            jsonObjectParser.getNearestPlaces(URL);
        } else
            onSuccess(null);

    }
}
