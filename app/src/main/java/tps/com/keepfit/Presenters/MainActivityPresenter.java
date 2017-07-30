package tps.com.keepfit.Presenters;

import tps.com.keepfit.Interactors.MainActivityInteractor;
import tps.com.keepfit.Interfaces.IMainActivityPresenter;
import tps.com.keepfit.Interfaces.IMainActivityViews;

/**
 * Created by aayman on 7/30/2017.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    MainActivityInteractor mMainActivityInteractor;
    IMainActivityViews iMainActivityViews;

    public MainActivityPresenter(IMainActivityViews iActivityViews) {
        mMainActivityInteractor = new MainActivityInteractor();
        iMainActivityViews = iActivityViews;
    }

    @Override
    public void loadExercises() {
        iMainActivityViews.displayViewData(mMainActivityInteractor.getCardsData(0));
    }

    @Override
    public void loadMeals() {
        iMainActivityViews.displayViewData(mMainActivityInteractor.getCardsData(1));
    }
}
