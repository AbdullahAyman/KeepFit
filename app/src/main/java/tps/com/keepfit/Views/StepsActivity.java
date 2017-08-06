package tps.com.keepfit.Views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.IOException;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.Prefrences.BakingSharedPreference;
import tps.com.keepfit.R;

public class StepsActivity extends AppCompatActivity {

    static int currentStepId = 0;

    static SimpleExoPlayer player;
    long currentPeriodMillisecond = 0;

    @BindView(R.id.steps_description)
    TextView stepDescription;
    @BindView(R.id.player_view)
    SimpleExoPlayerView exoPlayerView;
    @BindView(R.id.youtube_view)
    YouTubePlayerView youTubePlayerView;

    @BindView(R.id.tv_next)
    TextView nextStep;
    @BindView(R.id.tv_previous)
    TextView previousStep;
    @BindString(R.string.baseURL)
    String baseURL;

    Bundle bundle;
    List<CardsDataModel_> mCardListData;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(getString(R.string.currentStepId), currentStepId);
        if (player != null) {
            outState.putLong(getString(R.string.CURRENT_PLAYER_PERIOD), player.getCurrentPosition());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            currentStepId = savedInstanceState.getInt(getString(R.string.currentStepId));
            currentPeriodMillisecond = savedInstanceState.getLong(getString(R.string.CURRENT_PLAYER_PERIOD));
            displayStepNo(currentStepId);
            try {
                player.seekTo(currentPeriodMillisecond);
            } catch (Exception e) {
                player.seekTo(0);

            }

        }
    }

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

        //setupView();
        /*Log.i("url: ",baseURL + mCardListData.get(0).getCardVideoURL());
        myVideoView.setVideoURI(Uri.parse(baseURL + mCardListData.get(0).getCardVideoURL()));
        myVideoView.setMediaController(new MediaController(this));
        myVideoView.requestFocus();
        myVideoView.start();*/
    }


    private void setupView() {

        displayStepNo(currentStepId);
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
    }


    private void displayStepNo(int currentStepId) {
        createExoPlayer(mCardListData.get(currentStepId).getCardVideoURL());
        stepDescription.setText(mCardListData.get(currentStepId).getCardDescription());
    }

    private void createExoPlayer(String url) {
        //Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();
        // 2. Create the player

        player =
                ExoPlayerFactory.newSimpleInstance(StepsActivity.this, trackSelector, loadControl);
        exoPlayerView.setPlayer(player);
        preparePlayer(url);
        player.setPlayWhenReady(true);

        try {
            player.seekTo(currentPeriodMillisecond);
        } catch (Exception e) {
            player.seekTo(0);

        }


    }

    private void preparePlayer(String url) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(StepsActivity.this,
                Util.getUserAgent(StepsActivity.this, getResources().getString(R.string.app_name)), bandwidthMeter);
        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // This is the MediaSource representing the media to be played.

        Uri mp4VideoUri = Uri.parse(getPackageName() + "/raw/jumping_jacks_video");
        //         /* + R.raw.jumping_jacks_video*/);
        //   /*baseURL + url*/R.raw.jumping_jacks_video);

        MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri,
                dataSourceFactory, extractorsFactory, null, new ExtractorMediaSource.EventListener() {
            @Override
            public void onLoadError(IOException error) {
                Toast.makeText(StepsActivity.this, getResources().getString(R.string.errorLoading), Toast.LENGTH_SHORT).show();
            }
        });
        // Prepare the player with the source.
        player.prepare(videoSource);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (player != null) {
            BakingSharedPreference bakingSharedPreference = new BakingSharedPreference(StepsActivity.this);
            bakingSharedPreference.saveStringToSharedPreference(getString(R.string.CURRENT_PLAYER_PERIOD), player.getCurrentPosition() + "");
            bakingSharedPreference.saveStringToSharedPreference(getString(R.string.currentStepId), currentStepId + "");
            player.stop();
            player.release();
            player = null;
        }
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
