package tps.com.keepfit.Presenters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import tps.com.keepfit.DataModel.CardsDataModel;
import tps.com.keepfit.Interactors.MainActivityInteractor;
import tps.com.keepfit.Interfaces.IMainActivityPresenter;
import tps.com.keepfit.Interfaces.IMainActivityViews;

/**
 * Created by aayman on 7/30/2017.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    MainActivityInteractor mMainActivityInteractor;
    IMainActivityViews iMainActivityViews;
    ArrayList<String> imagesURL = new ArrayList<>();
    CardsDataModel cardsDataModel;
    public MainActivityPresenter(IMainActivityViews iActivityViews) {

        mMainActivityInteractor = new MainActivityInteractor();
        iMainActivityViews = iActivityViews;
    }

    @Override
    public void loadExercises() {
        loadImagesFromDB();
        cardsDataModel = mMainActivityInteractor.getCardsData(0);


    }

    @Override
    public void loadMeals() {
        iMainActivityViews.displayViewData(mMainActivityInteractor.getCardsData(1));
    }

    public void loadImagesFromDB() {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference("exercisesImages");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imagesURL = new ArrayList<>();
                imagesURL.add(dataSnapshot.child("jumping_jacks").getValue().toString());
                imagesURL.add(dataSnapshot.child("wall_sits").getValue().toString());
                imagesURL.add(dataSnapshot.child("push_ups").getValue().toString());
                imagesURL.add(dataSnapshot.child("abdominal_crunches").getValue().toString());
                imagesURL.add(dataSnapshot.child("step_ups_onto_a_chair").getValue().toString());
                imagesURL.add(dataSnapshot.child("squats").getValue().toString());
                imagesURL.add(dataSnapshot.child("triceps_dips_on_a_chair").getValue().toString());
                imagesURL.add(dataSnapshot.child("planks").getValue().toString());
                imagesURL.add(dataSnapshot.child("high_knees_running_in_place").getValue().toString());
                imagesURL.add(dataSnapshot.child("lunges").getValue().toString());
                imagesURL.add(dataSnapshot.child("push_ups_and_rotations").getValue().toString());
                imagesURL.add(dataSnapshot.child("side_planks").getValue().toString());
                for (int i = 0; i < imagesURL.size(); i++) {
                    cardsDataModel.getCardsDataModel().get(i).setCardImageUrl(imagesURL.get(i));
                }
                iMainActivityViews.displayViewData(cardsDataModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addValueEventListener(postListener);
        myRef.child("jumping_jacks").setValue("https://firebasestorage.googleapis.com/v0/b/keepfit-c90f0.appspot.com/o/jumping.jpg?alt=media&token=fc892009-6658-4997-85c9-cc2b2d800526");
    }
}
