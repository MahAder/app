package ader.getout;

import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ader.getout.PLACE_TYPE.CheckPoint;

public class MapActivity extends FragmentActivity {
    private Button close;
    private DataManager dm = null;
    private Place myPlace;
    private ListView listView;
    private ListView listView1;
    private GoogleMap mMap;// Might be null if Google Play services APK is not available.
    private CheckBox scan;
    private Map<String, Place> dictionary;
    private RelativeLayout relativeLayout;
    private ArrayList<Place> allPlaces;
    private Button type;
    private MediaPlayer mediaPlayer;

    public MapActivity() {
        dictionary = new HashMap<String, Place>();
        allPlaces = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        type = (Button) findViewById(R.id.button8);

        close = (Button) findViewById(R.id.button5);

        scan = (CheckBox) findViewById(R.id.checkBox);

        ///////////listView1/////////////////////////////
        listView = (ListView) findViewById(R.id.listView);
        listView.setVisibility(View.GONE);
        String[] values = {PLACE_TYPE.CheckPoint.toString(), PLACE_TYPE.Mushroom.toString(), PLACE_TYPE.Coin.toString(), PLACE_TYPE.Chest.toString(), PLACE_TYPE.Manhole.toString()};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        //////////////////////////////////////////////////////////////////////////

        ///////////listView2///////////////////////////////
        listView1 = (ListView) findViewById(R.id.listView2);
        listView1.setVisibility(View.GONE);
        String[] items = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items);
        listView1.setAdapter(adapter1);
        /////////////////////////////////////////////////////////////////////////

        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        type.setText("HYBRID");


        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void init() {

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            boolean loc = true;

            @Override
            public void onMyLocationChange(Location location) {
                LatLng myLocation;
                myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                if (loc) {
                    float zoom = 15;
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myLocation.latitude, myLocation.longitude)));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));
                    zoom = mMap.getCameraPosition().zoom;
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));
                }
                loc = false;
            }
        });
        //
        //////////CreatePlace///////////////////////////////////////
        dm = DataManager.getInstance();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                if (!scan.isChecked()) {
                    listView.setVisibility(View.VISIBLE);

                    close.setVisibility(View.VISIBLE);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            switch (position) {
                                case 0:
                                    listView.setVisibility(View.INVISIBLE);
                                    listView1.setVisibility(View.VISIBLE);
                                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            myPlace = dm.myObject(latLng.latitude, latLng.longitude, 0, "", "CheckPoint", position + 1, "By ADMIN", GENDER_TYPE.Male, PLACE_TYPE.CheckPoint);
                                            dictionary.put(myPlace.getId(), myPlace);
                                            allPlaces.add(myPlace);
                                            if (myPlace != null)
                                                mMap.addMarker(new MarkerOptions()
                                                                .position(new LatLng(myPlace.getLat(), myPlace.getLon()))
                                                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_checkpoint2))
                                                                .title(myPlace.getName())
                                                                .snippet("level: " + String.valueOf(myPlace.getLevel() + "  " + myPlace.getPlace().toString() + "  " + myPlace.getDesc()))
                                                );
                                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myPlace.getLat(), myPlace.getLon())));
                                            listView1.setVisibility(View.INVISIBLE);
                                            close.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                    break;
                                case 1:
                                    listView.setVisibility(View.INVISIBLE);
                                    listView1.setVisibility(View.VISIBLE);
                                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            myPlace = dm.myObject(latLng.latitude, latLng.longitude, 0, "", "Mushroom", position + 1, "By ADMIN", GENDER_TYPE.Male, PLACE_TYPE.Mushroom);
                                            dictionary.put(myPlace.getId(), myPlace);
                                            allPlaces.add(myPlace);
                                            if (myPlace != null)
                                                mMap.addMarker(new MarkerOptions()
                                                                .position(new LatLng(myPlace.getLat(), myPlace.getLon()))
                                                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_mushroom2))
                                                                .title(myPlace.getName())
                                                                .snippet("level: " + String.valueOf(myPlace.getLevel() + "  " + myPlace.getPlace().toString() + "  " + myPlace.getDesc()))
                                                );
                                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myPlace.getLat(), myPlace.getLon())));
                                            listView1.setVisibility(View.INVISIBLE);
                                            close.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                    break;
                                case 2:
                                    listView.setVisibility(View.INVISIBLE);
                                    listView1.setVisibility(View.VISIBLE);
                                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            myPlace = dm.myObject(latLng.latitude, latLng.longitude, 0, "", "Coin", position + 1, "By ADMIN", GENDER_TYPE.Male, PLACE_TYPE.Coin);
                                            dictionary.put(myPlace.getId(), myPlace);
                                            allPlaces.add(myPlace);
                                            if (myPlace != null)
                                                mMap.addMarker(new MarkerOptions()
                                                                .position(new LatLng(myPlace.getLat(), myPlace.getLon()))
                                                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_coin))
                                                                .title(myPlace.getName())
                                                                .snippet("level: " + String.valueOf(myPlace.getLevel() + "  " + myPlace.getPlace().toString() + "  " + myPlace.getDesc()))
                                                );
                                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myPlace.getLat(), myPlace.getLon())));
                                            listView1.setVisibility(View.INVISIBLE);
                                            close.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                    break;
                                case 3:
                                    myPlace = dm.myObject(latLng.latitude, latLng.longitude, 0, "", "Chest", 0, "By ADMIN", GENDER_TYPE.Male, PLACE_TYPE.Chest);
                                    dictionary.put(myPlace.getId(), myPlace);
                                    allPlaces.add(myPlace);
                                    if (myPlace != null)
                                        mMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(myPlace.getLat(), myPlace.getLon()))
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_chest))
                                                        .title(myPlace.getName())
                                                        .snippet("level: " + String.valueOf(myPlace.getLevel() + "  " + myPlace.getPlace().toString() + "  " + myPlace.getDesc()))
                                        );
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myPlace.getLat(), myPlace.getLon())));
                                    listView.setVisibility(View.INVISIBLE);
                                    close.setVisibility(View.INVISIBLE);
                                    break;
                                case 4:
                                    listView.setVisibility(View.INVISIBLE);
                                    listView1.setVisibility(View.VISIBLE);
                                    if (myPlace != null)
                                        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                myPlace = dm.myObject(latLng.latitude, latLng.longitude, 0, "", "Manhole", position + 1, "By ADMIN", GENDER_TYPE.Male, PLACE_TYPE.Manhole);
                                                dictionary.put(myPlace.getId(), myPlace);
                                                allPlaces.add(myPlace);
                                                mMap.addMarker(new MarkerOptions()
                                                                .position(new LatLng(myPlace.getLat(), myPlace.getLon()))
                                                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_roadhatch))
                                                                .title(myPlace.getName())
                                                                .snippet("level: " + String.valueOf(myPlace.getLevel() + "  " + myPlace.getPlace().toString() + "  " + myPlace.getDesc()))
                                                );
                                                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myPlace.getLat(), myPlace.getLon())));
                                                listView1.setVisibility(View.GONE);
                                                close.setVisibility(View.GONE);
                                            }
                                        });
                                    break;
                            }
                        }
                    });
                }

                if (scan.isChecked()) {
                    mediaPlayer = MediaPlayer.create(MapActivity.this, R.raw.scan_2);
                    mediaPlayer.start();
                    ArrayList<Place> alPl = new ArrayList<Place>();
                    alPl = dm.ParseScanResponse(latLng.latitude, latLng.longitude);
                    if (alPl != null) {

                        for (int i = 0; i < alPl.size(); i++) {
                            if (!dictionary.containsKey(alPl.get(i).getId())) {
                                switch (alPl.get(i).getPlace()) {
                                    case PotOfGold:
                                        mMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(alPl.get(i).getLat(), alPl.get(i).getLon()))
                                                                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_house3))
                                                        .title(alPl.get(i).getName())
                                                        .snippet("level: " + String.valueOf(alPl.get(i).getLevel()) + "  " + alPl.get(i).getPlace().toString() + "  " + alPl.get(i).getDesc())
                                        );
                                        break;


                                    case Chest:
                                        mMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(alPl.get(i).getLat(), alPl.get(i).getLon()))
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_chest))
                                                        .title(alPl.get(i).getName())
                                                        .snippet("level: " + String.valueOf(alPl.get(i).getLevel()) + "  " + alPl.get(i).getPlace().toString() + "  " + alPl.get(i).getDesc())
                                        );

                                        break;


                                    case CheckPoint:
                                        mMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(alPl.get(i).getLat(), alPl.get(i).getLon()))
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_checkpoint2))
                                                        .title(alPl.get(i).getName())
                                                        .snippet("level: " + String.valueOf(alPl.get(i).getLevel()) + "  " + alPl.get(i).getPlace().toString() + "  " + alPl.get(i).getDesc())
                                        );

                                        break;
                                    case Manhole:
                                        mMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(alPl.get(i).getLat(), alPl.get(i).getLon()))
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_roadhatch))
                                                        .title(alPl.get(i).getName())
                                                        .snippet("level: " + String.valueOf(alPl.get(i).getLevel()) + "  " + alPl.get(i).getPlace().toString() + "  " + alPl.get(i).getDesc())
                                        );
                                        break;

                                    case Mushroom:
                                        mMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(alPl.get(i).getLat(), alPl.get(i).getLon()))
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_mushroom2))
                                                        .title(alPl.get(i).getName())
                                                        .snippet("level: " + String.valueOf(alPl.get(i).getLevel()) + "  " + alPl.get(i).getPlace().toString() + "  " + alPl.get(i).getDesc())

                                        );

                                        break;

                                    case Coin:
                                        mMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(alPl.get(i).getLat(), alPl.get(i).getLon()))
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.qr_coin))
                                                        .title(alPl.get(i).getName())
                                                        .snippet("level: " + String.valueOf(alPl.get(i).getLevel()) + "  " + alPl.get(i).getPlace().toString() + "  " + alPl.get(i).getDesc())

                                        );

                                        break;
                                }
                                allPlaces.add(alPl.get(i));
                                dictionary.put(alPl.get(i).getId(), alPl.get(i));
                            }
                        }


                    }
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {

                relativeLayout = (RelativeLayout) findViewById(R.id.RelLay1);
                relativeLayout.setVisibility(View.VISIBLE);
                Button delete = (Button) findViewById(R.id.button4);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("delete", "ok");
                        for (int i = 0; i < allPlaces.size(); i++) {
                            if (marker.getPosition().latitude == allPlaces.get(i).getLat() && marker.getPosition().longitude == allPlaces.get(i).getLon()) {
                                Place pickUpPlace = dm.pickUp(allPlaces.get(i));
                                if (pickUpPlace.getPlace().equals(CheckPoint) && pickUpPlace.getLevel() > 1) {
                                    while (pickUpPlace.getLevel() > 1) {
                                        pickUpPlace.setLevel(pickUpPlace.getLevel() - 1);
                                        dm.pickUp(pickUpPlace);
                                    }
                                } else {
                                    dm.pickUp(allPlaces.get(i));
                                }
                                Log.d("pickUp", "ok");
                                dictionary.remove(allPlaces.get(i).getId());
                                allPlaces.remove(i);
                                marker.setVisible(false);
                                break;

                            }
                        }

                        relativeLayout.setVisibility(View.GONE);
                    }
                });

                Button cancel = (Button) findViewById(R.id.button6);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        relativeLayout.setVisibility(View.GONE);
                    }
                });
            }
        });


        /////////Scan2////////////////////////////////////


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.GONE);
                listView1.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
            }
        });

        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_TERRAIN) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    type.setText("TERRAIN");
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    type.setText("HYBRID");
                }
            }
        });

    }
}

