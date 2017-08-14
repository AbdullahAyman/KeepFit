package tps.com.keepfit.Interfaces;

import android.content.res.TypedArray;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by aayman on 7/30/2017.
 */

public interface IMainActivityInteractor {

    TypedArray loadWorkOutImages();

    ArrayList<StorageReference> loadWorkOutImagesURL();

    String[] loadWorkOutTimes();

    String[] loadWorkOutNames();

    String[] loadWorkOutDescription();

    String[] loadWorkOutVideos();

    TypedArray loadMealsImages();

    String[] loadMealsTimes();

    String[] loadMealsNames();

    String[] loadMealsDescription();

    String[] loadMealsVideos();
}
