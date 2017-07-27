package tps.com.keepfit;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TypedArray imgs = getResources().obtainTypedArray(R.array.workout_images);


        //mImgView1.setImageResource(imgs.getResourceId(i, -1));

    }
}
