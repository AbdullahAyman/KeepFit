package tps.com.keepfit.DataModel;

/**
 * Created by aayman on 7/30/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardsDataModel implements Parcelable {

    public final static Parcelable.Creator<CardsDataModel> CREATOR = new Creator<CardsDataModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CardsDataModel createFromParcel(Parcel in) {
            CardsDataModel instance = new CardsDataModel();
            in.readList(instance.cardsDataModel, (CardsDataModel_.class.getClassLoader()));
            return instance;
        }

        public CardsDataModel[] newArray(int size) {
            return (new CardsDataModel[size]);
        }

    };
    @SerializedName("CardsDataModel")
    @Expose
    private List<CardsDataModel_> cardsDataModel = null;

    public List<CardsDataModel_> getCardsDataModel() {
        return cardsDataModel;
    }

    public void setCardsDataModel(List<CardsDataModel_> cardsDataModel) {
        this.cardsDataModel = cardsDataModel;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(cardsDataModel);
    }

    public int describeContents() {
        return 0;
    }

}

