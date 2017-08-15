package tps.com.keepfit.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardsDataModel_ implements Parcelable {

    public final static Creator<CardsDataModel_> CREATOR = new Creator<CardsDataModel_>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CardsDataModel_ createFromParcel(Parcel in) {
            CardsDataModel_ instance = new CardsDataModel_();
            instance.cardId = ((long) in.readValue((long.class.getClassLoader())));
            instance.cardName = ((String) in.readValue((String.class.getClassLoader())));
            instance.cardDescription = ((String) in.readValue((String.class.getClassLoader())));
            instance.cardImage = ((long) in.readValue((long.class.getClassLoader())));
            instance.cardVideoURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.cardDuration = ((String) in.readValue((String.class.getClassLoader())));
            instance.cardImageUrl = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CardsDataModel_[] newArray(int size) {
            return (new CardsDataModel_[size]);
        }

    };
    @SerializedName("cardId")
    @Expose
    private long cardId;
    @SerializedName("cardName")
    @Expose
    private String cardName;
    @SerializedName("cardDescription")
    @Expose
    private String cardDescription;
    @SerializedName("cardImage")
    @Expose
    private long cardImage;
    @SerializedName("cardImageUrl")
    @Expose
    private String cardImageUrl;
    @SerializedName("cardVideoURL")
    @Expose
    private String cardVideoURL;
    @SerializedName("cardDuration")
    @Expose
    private String cardDuration;

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public long getCardImage() {
        return cardImage;
    }

    public void setCardImage(long cardImage) {
        this.cardImage = cardImage;
    }

    public String getCardVideoURL() {
        return cardVideoURL;
    }

    public void setCardVideoURL(String cardVideoURL) {
        this.cardVideoURL = cardVideoURL;
    }

    public String getCardDuration() {
        return cardDuration;
    }

    public void setCardDuration(String cardDuration) {
        this.cardDuration = cardDuration;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cardId);
        dest.writeValue(cardName);
        dest.writeValue(cardDescription);
        dest.writeValue(cardImage);
        dest.writeValue(cardVideoURL);
        dest.writeValue(cardDuration);
        dest.writeValue(cardImageUrl);
    }

    public int describeContents() {
        return 0;
    }

}
