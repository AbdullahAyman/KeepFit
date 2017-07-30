package tps.com.keepfit.Interactors;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import tps.com.keepfit.DataModel.CardsDataModel;
import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.Interfaces.IMainActivityInteractor;
import tps.com.keepfit.R;
import tps.com.keepfit.utilities.KeepFitApp;

/**
 * Created by aayman on 7/30/2017.
 */

public class MainActivityInteractor implements IMainActivityInteractor {
    Context mContext;

    public MainActivityInteractor() {
        mContext = KeepFitApp.newInstance().getContext();
    }

    public CardsDataModel getCardsData(int listType) {

        // 0 means getting exercise Cards data
        if (listType == 0)
            return getExerciseCards();
        else
            return getMealsCards();

    }

    private CardsDataModel getExerciseCards() {
        CardsDataModel cardsDataModel = new CardsDataModel();
        List<CardsDataModel_> cardsDataList = new ArrayList<>();
        TypedArray workOutImages = loadWorkOutImages();
        String[] workOutTimes = loadWorkOutTimes();
        String[] workOutNames = loadWorkOutNames();
        String[] workOutDescription = loadWorkOutDescription();
        String[] workOutVideos = loadWorkOutVideos();
        for (int i = 0; i < workOutImages.length(); i++) {
            CardsDataModel_ cardsDataItem = new CardsDataModel_();
            cardsDataItem.setCardDescription(workOutDescription[i]);
            cardsDataItem.setCardDuration(workOutTimes[i]);
            cardsDataItem.setCardImage(workOutImages.getResourceId(i, -1));
            cardsDataItem.setCardName(workOutNames[i]);
            cardsDataItem.setCardVideoURL(workOutVideos[i]);
            cardsDataList.add(cardsDataItem);
        }
        cardsDataModel.setCardsDataModel(cardsDataList);
        return cardsDataModel;
    }

    private CardsDataModel getMealsCards() {
        CardsDataModel cardsDataModel = new CardsDataModel();
        List<CardsDataModel_> cardsDataList = new ArrayList<>();
        TypedArray workOutImages = loadWorkOutImages();
        String[] loadMealsTimes = loadMealsTimes();
        String[] loadMealsNames = loadMealsNames();
        String[] loadMealsDescription = loadMealsDescription();
        String[] loadMealsVideos = loadMealsVideos();
        for (int i = 0; i < workOutImages.length(); i++) {
            CardsDataModel_ cardsDataItem = new CardsDataModel_();
            cardsDataItem.setCardDescription(loadMealsDescription[i]);
            cardsDataItem.setCardDuration(loadMealsTimes[i]);
            cardsDataItem.setCardImage(workOutImages.getResourceId(i, -1));
            cardsDataItem.setCardName(loadMealsNames[i]);
            cardsDataItem.setCardVideoURL(loadMealsVideos[i]);
            cardsDataList.add(cardsDataItem);
        }
        cardsDataModel.setCardsDataModel(cardsDataList);
        return cardsDataModel;
    }


    @Override
    public TypedArray loadWorkOutImages() {
        return mContext.getResources().obtainTypedArray(R.array.workout_images);
    }

    @Override
    public String[] loadWorkOutTimes() {
        return mContext.getResources().getStringArray(R.array.workout_times);
    }

    @Override
    public String[] loadWorkOutNames() {
        return mContext.getResources().getStringArray(R.array.workout_names);
    }

    @Override
    public String[] loadWorkOutDescription() {
        return mContext.getResources().getStringArray(R.array.workout_descriptions);
    }

    @Override
    public String[] loadWorkOutVideos() {
        return mContext.getResources().getStringArray(R.array.workout_videos);
    }


    @Override
    public TypedArray loadMealsImages() {
        return mContext.getResources().obtainTypedArray(R.array.workout_images);
    }

    @Override
    public String[] loadMealsTimes() {
        return mContext.getResources().getStringArray(R.array.workout_times);
    }

    @Override
    public String[] loadMealsNames() {
        return mContext.getResources().getStringArray(R.array.workout_names);
    }

    @Override
    public String[] loadMealsDescription() {
        return mContext.getResources().getStringArray(R.array.workout_descriptions);
    }

    @Override
    public String[] loadMealsVideos() {
        return mContext.getResources().getStringArray(R.array.workout_videos);
    }
}