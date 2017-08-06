package tps.com.keepfit.utilities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.R;

/**
 * Created by aayman on 8/1/2017.
 */

public class YoutubeVideoPlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static String YOUTUBE_API_KEY = "AIzaSyCZ68uVweLsjZjFjNreE9jl_dSPbKlSuP4";
    static int currentStepId = 0;
    Bundle bundle;
    List<CardsDataModel_> mCardListData;
    @BindView(R.id.youtube_view)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.tv_next)
    TextView nextStep;
    @BindView(R.id.tv_previous)
    TextView previousStep;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            mCardListData = bundle.getParcelableArrayList(getString(R.string.mCardListOfData));
            currentStepId = bundle.getInt(getString(R.string.currentStepId));
        }
        youTubePlayerView.initialize(YOUTUBE_API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        /**
         *  Start buffering
         */
        if (!wasRestored) {
            youTubePlayer.cueVideo(/*mCardListData.get(0).getCardVideoURL()*/"aJ7BoNG-r2c");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, getString(R.string.errorLoading), Toast.LENGTH_LONG).show();
    }
    /*private void setupView() {


        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStepId > 0) {
                    currentStepId--;
                    displayStepNo(currentStepId);
                } else
                    Toast.makeText(StepsActivity.this, getResources().getString(R.string.firstStep), Toast.LENGTH_SHORT).show();

            }
        });
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStepId < mCardListData.size() - 1) {
                    currentStepId++;
                    displayStepNo(currentStepId);
                } else
                    Toast.makeText(StepsActivity.this, getResources().getString(R.string.lastStep), Toast.LENGTH_SHORT).show();

            }
        });
    }*/

}
