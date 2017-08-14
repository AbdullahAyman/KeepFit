package tps.com.keepfit.Views;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import tps.com.keepfit.DataModel.GoogleGYMPlaces.NearestGymPlaces;
import tps.com.keepfit.DataModel.GoogleGYMPlaces.Result;
import tps.com.keepfit.Interfaces.INearestPlacesPresenter;
import tps.com.keepfit.Interfaces.INearestPlacesView;
import tps.com.keepfit.Presenters.NearestPlacesPresenter;
import tps.com.keepfit.R;

public class NearestPlacesActivity extends FragmentActivity implements OnMapReadyCallback, INearestPlacesView {

    INearestPlacesPresenter iNearestPlacesView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_places);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        iNearestPlacesView = new NearestPlacesPresenter(this);
        iNearestPlacesView.retrieveNearestPlaces();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
    }

    @Override
    public void assignDataToMap(NearestGymPlaces nearestGymPlaces) {
        if (nearestGymPlaces != null) {
            if (mMap != null) {
                if (nearestGymPlaces.getStatus() == "200") {
                    List<Result> mResults = nearestGymPlaces.getResults();
                    for (int i = 0; i < mResults.size(); i++) {
                        LatLng sydney = new LatLng(mResults.get(i).getGeometry().getLocation().getLat(),
                                mResults.get(i).getGeometry().getLocation().getLng());
                        mMap.addMarker(new MarkerOptions().position(sydney).title(mResults.get(i).getName()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    }
                }
            }
        } else {
            Toast.makeText(this, "unavailable location search!", Toast.LENGTH_SHORT).show();
        }
    }
}
