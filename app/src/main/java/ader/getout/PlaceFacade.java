package ader.getout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by User on 18.05.2015.
 */
public class PlaceFacade {
    private UUID placeId;
    private double latitude;
    private double longitude;
    private double altitude;
    private String macAddress;

    public PlaceFacade(Place place)
    {
        this.placeId = UUID.fromString(place.getId());
        this.latitude = place.getLat();
        this.longitude = place.getLon();
        this.altitude = place.getAl();
        this.macAddress = place.getMac();
    }

    public PlaceFacade(String placeId,  Double longitude, Double latitude, Double altitude, String macAddress) {
        this.placeId = UUID.fromString(placeId);
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.macAddress = macAddress;
    }

    public UUID getPlaceId() {
        return placeId;
    }

    public void setPlaceId(UUID placeId) {
        this.placeId = placeId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public JSONObject placeFacadeJson()
    {
        JSONObject input = new JSONObject();
        try {
            input.put("placeId", placeId);
            input.put("latitude", latitude);
            input.put("longitude", longitude);
            input.put("altitude", altitude);
            input.put("macAddress", macAddress);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return input;
    }
}
