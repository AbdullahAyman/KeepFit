package tps.com.keepfit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;
import tps.com.keepfit.Adapters.MainCardsAdapter;
import tps.com.keepfit.DataModel.CardsDataModel;
import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.Interfaces.IMainActivityPresenter;
import tps.com.keepfit.Interfaces.IMainActivityViews;
import tps.com.keepfit.Presenters.MainActivityPresenter;
import tps.com.keepfit.utilities.KeepFitApp;

public class MainActivity extends AppCompatActivity implements IMainActivityViews, View.OnClickListener {

    @BindView(R.id.play_fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.tv_exercise)
    TextView exercises;
    @BindView(R.id.tv_meals)
    TextView meals;
    @BindView(R.id.recycler_swipe_refresh)
    SwipyRefreshLayout pullRefreshLayout;
    @BindView(R.id.all_recycler_view)
    RecyclerView mRecyclerView;
    @BindBool(R.bool.isTablet)
    boolean isTab;

    int tabID = 0;
    MainCardsAdapter mainCardsAdapter;
    IMainActivityPresenter mainActivityPresenter;
    List<CardsDataModel_> mCardListOfData;

    private void setUpGridRecyclerView() {
        GridLayoutManager lLayout;
        lLayout = new GridLayoutManager(this, 3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lLayout);
    }

    private void setUpRecycler() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void setupRefreshTool() {
        pullRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        pullRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                pullRefreshLayout.setRefreshing(true);
                Log.d("MainActivity", "Refresh triggered at "
                        + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    loadData(tabID);
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRefreshTool();
        if (isTab)
            setUpGridRecyclerView();
        else
            setUpRecycler();
        mainActivityPresenter = new MainActivityPresenter(this);

        exercises.setOnClickListener(this);

        meals.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        this.onClick(exercises);
    }

    private void loadData(int i) {
        showProgress();
        tabID = i;
        if (i == 0)
            mainActivityPresenter.loadExercises();
        else
            mainActivityPresenter.loadMeals();
    }


    @Override
    public void showProgress() {
        pullRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        pullRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayViewData(CardsDataModel dataModel) {

        hideProgress();
        List<CardsDataModel_> cardListOfData = null;
        if (dataModel.getCardsDataModel() != null)
            cardListOfData = dataModel.getCardsDataModel();
        if (mainCardsAdapter == null)
            mainCardsAdapter = new MainCardsAdapter(this, KeepFitApp.newInstance().getPicasso());
        mRecyclerView.setAdapter(mainCardsAdapter);
        displayRecyclerData(cardListOfData);
    }

    private void displayRecyclerData(List<CardsDataModel_> cardListOfData) {
        if (cardListOfData != null) {
            mCardListOfData = cardListOfData;
            mainCardsAdapter.swapAdapterData(cardListOfData);
        }
    }

    @Override
    public void onClick(View v) {
        int Id = v.getId();
        switch (Id) {
            case R.id.tv_meals:
                loadData(1);
                resetViewColors();
                floatingActionButton.setVisibility(View.GONE);
                meals.setTextColor(getResources().getColor(R.color.colorPrimary));
                meals.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.tv_exercise:
                loadData(0);
                resetViewColors();
                floatingActionButton.setVisibility(View.VISIBLE);
                exercises.setTextColor(getResources().getColor(R.color.colorPrimary));
                exercises.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.play_fab:
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(getString(R.string.mCardListOfData), (ArrayList<? extends Parcelable>) mCardListOfData);
                FragmentPlayExercises mFragmentPlayExercises = new FragmentPlayExercises();
                mFragmentPlayExercises.setArguments(bundle);
                mFragmentPlayExercises.show(getFragmentManager().beginTransaction(),"Play Dialog");
                break;
            default:
                break;
        }
    }

    private void resetViewColors() {
        meals.setTextColor(getResources().getColor(R.color.colorWhite));
        meals.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        exercises.setTextColor(getResources().getColor(R.color.colorWhite));
        exercises.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

    }
}
