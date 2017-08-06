
package tps.com.keepfit.DataModel.GoogleGYMPlaces;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearestGymPlaces implements Parcelable {

    public final static Creator<NearestGymPlaces> CREATOR = new Creator<NearestGymPlaces>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NearestGymPlaces createFromParcel(Parcel in) {
            NearestGymPlaces instance = new NearestGymPlaces();
            in.readList(instance.htmlAttributions, (Object.class.getClassLoader()));
            in.readList(instance.results, (Result.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public NearestGymPlaces[] newArray(int size) {
            return (new NearestGymPlaces[size]);
        }

    };
    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(htmlAttributions);
        dest.writeList(results);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
