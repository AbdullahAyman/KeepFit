package tps.com.keepfit.Networking.Parser;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tps.com.keepfit.Networking.Interfaces.CallBackJSONObject;
import tps.com.keepfit.utilities.KeepFitApp;


//=================================================================
// this class contains all functions with return type JsonObject
//=================================================================
public class JSONObjectParser implements Callback<JsonObject> {

    CallBackJSONObject callBackJSONObject;

    //=================================================================
    // constructor for creating calling object for network instance
    //=================================================================
    public JSONObjectParser(CallBackJSONObject call) {
        this.callBackJSONObject = call;
    }

    //=================================================================
    // below is all web api methods with return type jsonObject
    //=================================================================

    public void getNearestPlaces(String Url) {
        Call<JsonObject> resString = KeepFitApp.newInstance().newRetrofitRequest().getData(Url);
        resString.enqueue(this);
    }

    //=================================================================
    // Response CallBack for success response
    //=================================================================
    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        callBackJSONObject.onSuccess(response);
    }

    //=================================================================
    // Response CallBack for error response
    //=================================================================
    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        callBackJSONObject.OnFail(t);
    }
}
