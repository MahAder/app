package ader.getout;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by User on 06.05.2015.
 */
public class Place {
       private LatLng latLng;
       private String id;
       private String name ;
       private double lat ;
       private double lon ;
       private double al ;
       private String mac;
       private int level ;
       private String desc ;
       private GENDER_TYPE gend ;
       private PLACE_TYPE place ;
       private String acId ;
       private String im ;
      // private Date up ;

    DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

   // public Place(){
        //super();
   // }

    public Place(JSONObject jsonObject) throws JSONException, ParseException {

        this.id = ((jsonObject.getString("id")));
        this.name = (jsonObject.getString("name"));
        this.lon = (jsonObject.getDouble("longitude"));
        this.lat = ((jsonObject.getDouble("latitude")));
        this.al = ((jsonObject.getDouble("altitude")));
        this.mac = ((jsonObject.getString("macAddress")));
        this.level = ((jsonObject.getInt("level")));
        this.desc = ((jsonObject.getString("description")));
        this.gend = (GENDER_TYPE.valueOf(jsonObject.getString("genderType")));
        this.place = (PLACE_TYPE.valueOf( jsonObject.getString("placeType")));
        this.acId = ((jsonObject.getString("accountId")));
        this.im = ((jsonObject.getString("image")));
        //this.up = (format.parse(jsonObject.getString("updateDate")));
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAl() {
        return al;
    }

    public void setAl(double al) {
        this.al = al;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public GENDER_TYPE getGend() {
        return gend;
    }

    public void setGend(GENDER_TYPE gend) {
        this.gend = gend;
    }

    public PLACE_TYPE getPlace() {
        return place;
    }

    public void setPlace(PLACE_TYPE place) {
        this.place = place;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

   // public Date getUp() {
     //   return up;
   // }

   // public void setUp(Date up) {
     //   this.up = up;
  //  }
}
