package personal.jayhou.locationdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG = "LocationDemo";
    LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged:" + location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.d(TAG, "onStatusChanged:" + s);
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.d(TAG, "onStatusChanged:" + s);
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.d(TAG, "onProviderDisabled:" + s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_fused_location).setOnClickListener((view) -> {
            Log.d(TAG, "ON CLICK");
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mLocationListener);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((LocationManager) getSystemService(Context.LOCATION_SERVICE)).removeUpdates(mLocationListener);
    }
}
