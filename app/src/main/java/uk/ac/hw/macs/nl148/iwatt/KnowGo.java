package uk.ac.hw.macs.nl148.iwatt;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class KnowGo extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback , View.OnClickListener{


    private boolean mPermissionDenied = false;
    AutoCompleteTextView findMap;

    private GoogleMap mMap;
    private static final LatLng MAIN_RECEPTION= new LatLng( 55.909586, -3.320346);
    private static final LatLng EM_BUILDING = new LatLng(55.912208, -3.322321 );
    private static final LatLng CM_BUILDING = new LatLng(55.91255,-3.32189);
    private static final LatLng WP_BUILDING = new LatLng(55.911761, -3.321696 );
    private static final LatLng JM_BUILDING = new LatLng(55.912006, -3.321173);
    private static final LatLng JC_BUILDING = new LatLng(55.911913, -3.324239);
    private static final LatLng JN_BUILDING = new LatLng(55.911466, -3.323557 );
    private static final LatLng SR_BUILDING = new LatLng(55.911149, -3.322361);
    private static final LatLng DB_BUILDING = new LatLng(55.911432, -3.322478);
    private static final LatLng EC_BUILDING = new LatLng(55.911504, -3.325113 );
    private static final LatLng WA_BUILDING = new LatLng(55.911101, -3.324534);
    private static final LatLng WA2_BUILDING = new LatLng(55.910809, -3.324319);
    private static final LatLng PG_CENTER = new LatLng(55.912180, -3.323622);
    private static final LatLng MB_BUILDING = new LatLng(55.908651, -3.321012);
    private static final LatLng GC_LIBRARY = new LatLng(55.908802, -3.322166);
    private static final LatLng JWC_CENTER= new LatLng(55.909603, -3.319513);
    private static final LatLng JWC2_CENTER= new LatLng(55.909710, -3.318976);
    private static final LatLng SU_BUILDING= new LatLng(55.911260, -3.318268);
    private static final LatLng CHAPLAINCY= new LatLng(55.91067,-3.32364);
    private static final LatLng THE_PIECE= new LatLng(55.91048,-3.32232);
    private static final LatLng CAFE_BRIO= new LatLng(55.91034,-3.32211);



    Button earth;
    Button search;
    IterableMap< LatLng,String> locations = new HashedMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_go);

        earth = (Button) findViewById(R.id.earth);
        findMap = (AutoCompleteTextView) findViewById(R.id.mapfind);
        search = (Button) findViewById(R.id.search);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


        locations.put(MAIN_RECEPTION, "Main Reception");
        locations.put(EM_BUILDING, "Earl Mountbatten");
        locations.put(CM_BUILDING,"Colin Maclaurin");
        locations.put( WP_BUILDING ,"William Perking");
        locations.put( JM_BUILDING ,"John Muir");
        locations.put( JC_BUILDING ,"John Coulson");
        locations.put( JN_BUILDING ,"James Nasmyth");
        locations.put( SR_BUILDING ,"Scott Russel");
        locations.put( DB_BUILDING ,"David Brewster");
        locations.put( EC_BUILDING ,"Edwin Chadwick");
        locations.put( WA_BUILDING ,"William Arrol");
        locations.put( WA2_BUILDING ,"William Arrol");
        locations.put( PG_CENTER ,"PG Centre");
        locations.put( MB_BUILDING ,"Mary Burton");
        locations.put( GC_LIBRARY ,"Library");
        locations.put( JWC_CENTER ,"James Watt I");
        locations.put( JWC2_CENTER ,"James Watt II");
        locations.put( SU_BUILDING ,"Student Union ");
        locations.put( CHAPLAINCY ,"Chaplaincy");
        locations.put( THE_PIECE ," The Piece ");
        locations.put( CAFE_BRIO ," Cafe Brio ");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locations.values().toArray());
        Typeface tf = Typeface.createFromAsset(getAssets(), "Simple tfb.ttf");
        findMap.setThreshold(1);
        findMap.setTypeface(tf);
        findMap.setAdapter(arrayAdapter);

        search.setOnClickListener(this);
        earth.setOnClickListener(this);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);

        mMap.addMarker(new MarkerOptions().position(EM_BUILDING).title("Earl MountBatton Building"));//.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EM_BUILDING, 16.0f));

        mMap.addMarker(new MarkerOptions().position(CM_BUILDING).title("Collin Maclaurin Building"));


        mMap.addMarker(new MarkerOptions().position(WP_BUILDING).title("William Perking Building"));

        mMap.addMarker(new MarkerOptions().position(MAIN_RECEPTION).title("Heriot Watt University : Main Reception")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


        mMap.addMarker(new MarkerOptions().position(JM_BUILDING).title("John Muir Building"));

        mMap.addMarker(new MarkerOptions().position(JC_BUILDING).title("John Coulson Building"));


        mMap.addMarker(new MarkerOptions().position(JN_BUILDING).title("James Nasmyth Building"));

        mMap.addMarker(new MarkerOptions().position(SR_BUILDING).title("Scott Russel Building"));


        mMap.addMarker(new MarkerOptions().position(DB_BUILDING).title("David Brewster Building"));


        mMap.addMarker(new MarkerOptions().position(EC_BUILDING).title("Edwin Chadwick Building"));

        mMap.addMarker(new MarkerOptions().position(WA_BUILDING).title("William Arrol Building"));

        mMap.addMarker(new MarkerOptions().position(WA2_BUILDING).title("William Arrol Building"));

        mMap.addMarker(new MarkerOptions().position(PG_CENTER).title("Postgraduate Centre"));


        mMap.addMarker(new MarkerOptions().position(MB_BUILDING).title("Mary Burton Building"));


        mMap.addMarker(new MarkerOptions().position(GC_LIBRARY).title("Cameron Small Library").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        mMap.addMarker(new MarkerOptions().position(JWC_CENTER).title("James Watt Centre I"));

        mMap.addMarker(new MarkerOptions().position(JWC2_CENTER).title("James Watt Centre II"));

        mMap.addMarker(new MarkerOptions().position(SU_BUILDING).title("Heriot Watt Student Union : Student Union").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        mMap.addMarker(new MarkerOptions().position(CHAPLAINCY).title("Heriot Watt University: The Chaplaincy").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions().position(THE_PIECE).title("The Piece").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        mMap.addMarker(new MarkerOptions().position(CAFE_BRIO).title("Cafe Brio").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));


        mMap.getUiSettings();
        enableMyLocation();


    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {

        // Permission to access the location is missing.
        // Access to the location has been granted to the app.
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "This is your current location", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        // Display the missing permission error dialog when the fragments resume.
        mPermissionDenied = true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    boolean on = false;
    @Override
    public void onClick(View v) {

        if(earth == v && on == false)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            on = true;
        }
        else if(earth == v && on == true)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            on = false;
        }

        if(search == v)
        {
            MapIterator<LatLng,String> it = locations.mapIterator();
            while (it.hasNext()) {
                LatLng key = it.next();
                String value = it.getValue();
                if(findMap.getText().toString().contentEquals(value))
                {

                    mMap.clear();

                    mMap.addMarker(new MarkerOptions().position(key).title(value)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(key, 17.0f));



                }

            }
        }




    }
}