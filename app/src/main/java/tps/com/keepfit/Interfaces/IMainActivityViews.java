package tps.com.keepfit.Interfaces;

import tps.com.keepfit.DataModel.CardsDataModel;

/**
 * Created by aayman on 7/30/2017.
 */

public interface IMainActivityViews {
    void showProgress();

    void hideProgress();

    void displayViewData(CardsDataModel dataModel);

}
