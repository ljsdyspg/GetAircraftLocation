package com.spg.GetAircraftLocation;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import java.util.Map;

public class MapActivity extends Activity {

    private AMap aMap;
    private double aircraftLocationLat;
    private double aircraftLocationLng;
    private MapView mapView;
    private UiSettings uiSettings;
    private MarkerOptions markerOption;
    private MyLocationStyle myLocationStyle;

    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        aMap = mapView.getMap();

        initUI();
        showControllerPosition();
        mark();
    }

    private void initUI(){

        uiSettings = aMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮

        aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置
    }

    private void showControllerPosition(){
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
    }
    private void mark(){
        //getAircraftLocation();
        latLng = new LatLng(30.53139088,114.3571358);
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.num_three)))
                .position(latLng)
                .draggable(true);
        aMap.addMarker(markerOption);
        //Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));

    }
    private void getAircraftLocation(){
        aircraftLocationLat = getIntent().getDoubleExtra("aircraftLat",30.5517670000);//默认在武汉长江大桥
        aircraftLocationLng = getIntent().getDoubleExtra("aircraftLng",114.2832870000);
        latLng = new LatLng(aircraftLocationLat,aircraftLocationLng);
    }

}
