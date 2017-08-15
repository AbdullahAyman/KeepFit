package tps.com.keepfit.utilities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.R;
import tps.com.keepfit.Views.MainActivity;

/**
 * Created by aayman on 8/1/2017.
 */

public class YoutubeVideoPlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static String YOUTUBE_API_KEY = "AIzaSyAoZJhyUC1WkIdAjG-3k10BrpNQr9RIy5o";
    static int currentStepId = 0;
    Bundle bundle;
    List<CardsDataModel_> mCardListData;
    @BindView(R.id.youtube_view)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.steps_description)
    TextView stepDescription;
    @BindView(R.id.tv_next)
    TextView nextStep;
    @BindView(R.id.tv_previous)
    TextView previousStep;
    @BindView(R.id.toolbar_title)
    TextView titleView;
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };

    @OnClick(R.id.toolbar_back)
    void goBack() {
        Intent intent = new Intent(YoutubeVideoPlayer.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);
        findViewById(R.id.toolbar_menu_img).setVisibility(View.INVISIBLE);
        findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            mCardListData = bundle.getParcelableArrayList(getString(R.string.mCardListOfData));
            currentStepId = bundle.getInt(getString(R.string.currentStepId));
        }
        displayStepNo(currentStepId);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStepNo(++currentStepId);

            }
        });
        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStepNo(--currentStepId);
            }


        });

    }

    private void displayStepNo(int i) {
        if (i >= 0) {
            if (i >= mCardListData.size()) {
                Toast.makeText(this, getString(R.string.lastStep), Toast.LENGTH_SHORT).show();
                currentStepId = mCardListData.size() - 1;
            } else {
                stepDescription.setText(mCardListData.get(i).getCardDescription());
                youTubePlayerView.removeAllViews();
                youTubePlayerView.initialize(YOUTUBE_API_KEY, this);

                titleView.setText(mCardListData.get(i).getCardName());
            }
        } else {
            Toast.makeText(this, getString(R.string.firstStep), Toast.LENGTH_SHORT).show();
            currentStepId = 0;

        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        /**
         *  Start buffering
         */
        if (!wasRestored) {
            youTubePlayer.cueVideo(mCardListData.get(currentStepId).getCardVideoURL());
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, getString(R.string.errorLoading), Toast.LENGTH_LONG).show();
    }

}
