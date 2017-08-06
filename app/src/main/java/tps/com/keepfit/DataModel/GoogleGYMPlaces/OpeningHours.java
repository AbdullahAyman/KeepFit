
package tps.com.keepfit.DataModel.GoogleGYMPlaces;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpeningHours implements Parcelable {

    public final static Creator<OpeningHours> CREATOR = new Creator<OpeningHours>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OpeningHours createFromParcel(Parcel in) {
            OpeningHours instance = new OpeningHours();
            instance.openNow = ((boolean) in.readValue((boolean.class.getClassLoader())));
            in.readList(instance.weekdayText, (Object.class.getClassLoader()));
            return instance;
        }

        public OpeningHours[] newArray(int size) {
            return (new OpeningHours[size]);
        }

    };
    @SerializedName("open_now")
    @Expose
    private boolean openNow;
    @SerializedName("weekday_text")
    @Expose
    private List<Object> weekdayText = null;

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public List<Object> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(openNow);
        dest.writeList(weekdayText);
    }

    public int describeContents() {
        return 0;
    }

}
