package tps.com.keepfit.Views;

import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jesusm.holocircleseekbar.lib.HoloCircleSeekBar;

import java.util.List;

import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.R;

/**
 * Created by aayman on 7/31/2017.
 */

public class FragmentPlayExercises extends DialogFragment {

    Bundle bundle;
    List<CardsDataModel_> mCardListOfData;
    HoloCircleSeekBar picker;
    ImageView exercisePic;
    TextView exerciseDescription, exerciseName;
    int viewCounter = 0;
    int maxTime = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_down_play, container, false);
        initView(view);
        bundle = getArguments();
        if (bundle != null) {
            mCardListOfData = bundle.getParcelableArrayList(getString(R.string.mCardListOfData));

            updateView(mCardListOfData.get(0));

        }

        return view;
    }

    private void initView(final View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Light_NoActionBar_Overscan);
        } else {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);
        }
        picker = (HoloCircleSeekBar) view.findViewById(R.id.picker);
        exercisePic = (ImageView) view.findViewById(R.id.image_exercise);
        exerciseDescription = (TextView) view.findViewById(R.id.exercise_description);
        exerciseName = (TextView) view.findViewById(R.id.exercise_name);
        picker.setMax(maxTime);
        picker.setValue(maxTime);
    }

    private void startingUp(final int counter) {

        new CountDownTimer(counter * 1000, 1000) {
            final float[] count = {Float.parseFloat(counter + "")};

            public void onTick(long millisUntilFinished) {
                picker.setValue(count[0]--);

            }

            public void onFinish() {
                picker.setValue(count[0]--);
                /*picker.setMax(counter);
                count[0] = Float.parseFloat(counter + "");
                picker.setValue(count[0]);*/
                viewCounter++;
                if (viewCounter < mCardListOfData.size()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            picker.setValue(count[0]--);
                            updateView(mCardListOfData.get(viewCounter));
                        }
                    }, 1000);
                } else{
                    picker.setValue(count[0]--);
                    dismiss();
                }
            }
        }.start();


    }

    private void updateView(final CardsDataModel_ cardsDataModel_) {
        picker.setMax(maxTime);
        picker.setValue(maxTime);
        startingUp(maxTime);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exercisePic.setImageResource((int) cardsDataModel_.getCardImage());
                exerciseName.setText(cardsDataModel_.getCardName());
                exerciseDescription.setText(cardsDataModel_.getCardDescription());
            }
        }, 1000);


    }
    /*@Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }*/
}
