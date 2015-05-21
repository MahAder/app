package ader.getout;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;


/**
 * Created by User on 06.05.2015.
 */
public class DataManager {
    private static DataManager instance = new DataManager();

    public DataManager()
    {}

    public static DataManager getInstance()
    {
        return instance;
    }

    public ArrayList<Place> ParseScanResponse(double la, double lo)
    {
        ApiClient api = null;
        api = ApiClient.getInstance();
        Place pl;
        ArrayList<Place> arPl = new ArrayList<>() ;
        try {

            JSONObject object  = new JSONObject(api.scan2(la, lo));

            object.toString();

            JSONArray jsonarray =object.getJSONArray("places");


            for(int i = 0; i < jsonarray.length(); i++) {
                pl = new Place(jsonarray.getJSONObject(i));
                Log.d("a", jsonarray.getJSONObject(i).toString());
                arPl.add(pl);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return  arPl;
    }

    public Place myObject(double longitude, double latitude, double altitude, String macAddress, String name,  Integer level, String description, GENDER_TYPE genderType, PLACE_TYPE placeType)
    {
        ApiClient api = null;
        api = ApiClient.getInstance();
        Place place = null;
        try {

            JSONObject object  = new JSONObject(api.createPlace(longitude, latitude, altitude, macAddress, name, description, level, genderType, placeType));

            object.toString();

            place = new Place(object);

        } catch(Exception e){
            e.printStackTrace();
        }

        return  place;
    }

    public Place pickUp(Place place)
    {
        ApiClient api = null;
        api = ApiClient.getInstance();
        try {
            JSONObject object = new JSONObject(api.pickUp(place));
            place = new Place(object);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return place;
    }
}
