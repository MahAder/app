package ader.getout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 12.05.2015.
 */
public class PlaceBaseDTO{

    private Double longitude;
    private Double latitude;
    private Double altitude;
    private String macAddress;
    private String name;
    private Integer level;
    private String description;
    private GENDER_TYPE genderType;
    private PLACE_TYPE placeType;

    public PlaceBaseDTO() {
        super();
    }

    public PlaceBaseDTO(Place place) {
        this.name = place.getName();
        this.longitude = place.getLon();
        this.latitude = place.getLat();
        this.altitude = place.getAl();
        this.macAddress = place.getMac();
        this.level = place.getLevel();
        this.description = place.getDesc();
        this.genderType = place.getGend();
        this.placeType = place.getPlace();
    }

    public PlaceBaseDTO(Double longitude, Double latitude, Double altitude, String macAddress, String name,  Integer level, String description, GENDER_TYPE genderType, PLACE_TYPE placeType) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.macAddress = macAddress;
        this.name = name;
        this.level = level;
        this.description = description;
        this.genderType = genderType;
        this.placeType = placeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GENDER_TYPE getGenderType() {
        return genderType;
    }

    public void setGenderType(GENDER_TYPE genderType) {
        this.genderType = genderType;
    }

    public PLACE_TYPE getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PLACE_TYPE placeType) {
        this.placeType = placeType;
    }

    public JSONObject placeJson()
    {
        JSONObject input = new JSONObject();
        try {
            input.put("latitude", latitude);
            input.put("longitude", longitude);
            input.put("altitude", altitude);
            input.put("macAddress", macAddress);
            input.put("name", name);
            input.put("level", level);
            input.put("description", description);
            input.put("genderType", genderType);
            input.put("placeType", placeType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return input;
    }

}
