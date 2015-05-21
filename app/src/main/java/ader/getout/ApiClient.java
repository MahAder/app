package ader.getout;

import android.app.Application;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * Created by User on 01.05.2015.
 */
public class ApiClient extends Application {

    private static ApiClient instance = new ApiClient();

    public ApiClient() {
    }

    public static ApiClient getInstance() {
        return instance;
    }

    CookieManager cookieManager = CookieManager.getInstance();

    public boolean login(WebView mWebView) {

        //mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadUrl("https://ec2-54-175-242-149.compute-1.amazonaws.com/auth/facebook");

        return true;
    }

    public boolean userInfo(WebView mWebView) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://ec2-54-175-242-149.compute-1.amazonaws.com/api/acount/info");

        return true;
    }

    public String scan2(double lat, double lon) {

            String json = "";

            json = get("https://ec2-54-175-242-149.compute-1.amazonaws.com/api/map/scan2/" + String.valueOf(lat) + "/" + String.valueOf(lon) + "/0/Scan/100").toString();
            Log.d("a", json);
        return json;
    }

    public String createPlace(double latitude, double longitude, double altitude, String macAddress, String name, String description, Integer level,  GENDER_TYPE genderType, PLACE_TYPE placeType) {
        PlaceBaseDTO pbd = new PlaceBaseDTO(longitude, latitude, altitude, macAddress, name, level, description, genderType, placeType);


        String http = "https://ec2-54-175-242-149.compute-1.amazonaws.com/api/place/create";



        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String json1 = "";
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;

        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }

                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            /////////////////////////input////////////////////////////////////////

            String cookies1 = CookieManager.getInstance().getCookie(http);
            URL u = new URL(http);
            HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
            urlConnection.setRequestProperty("Cookie", cookies1);
            String id = Settings.Secure.ANDROID_ID;
            Log.d("id", id);
            urlConnection.setRequestProperty("deviceid", id);


            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            JSONObject input = pbd.placeJson();
            Log.d("a", input.toString());
            writer.write(input.toString());
            writer.flush();
            writer.close();

            if (urlConnection.getResponseCode() == 406)
            {
                json1 = String.valueOf(406);
            }
            //////////////////////////////////output////////////////////////////////////////

            InputStream is = urlConnection.getInputStream();
            rd = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }
            urlConnection.disconnect();

            json1 = sb.toString();
            Log.d("a", json1);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json1;
    }

    private StringBuilder get(String url) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String json = "";
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;

        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }

                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);



            String cookies1 = CookieManager.getInstance().getCookie(url);
            URL u = new URL(url);
            HttpURLConnection defaultClient = (HttpURLConnection) u.openConnection();
            defaultClient.setRequestProperty("Cookie", cookies1);
            System.setProperty("http.keepAlive", "false");
            defaultClient.setRequestMethod("GET");
            String id = Settings.Secure.ANDROID_ID;
            Log.d("id", id);
            defaultClient.setRequestProperty("deviceid", id);
            defaultClient.setDoOutput(false);
            rd = new BufferedReader(new InputStreamReader(defaultClient.getInputStream()));
            sb = new StringBuilder();

            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       return sb;
    }

    public String pickUp(Place place)
    {
       PlaceFacade placeFacade = new PlaceFacade(place);

        String http = "https://ec2-54-175-242-149.compute-1.amazonaws.com/api/place/pickup";



        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String json1 = "";
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;

        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }

                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            /////////////////////////input////////////////////////////////////////

            String cookies1 = CookieManager.getInstance().getCookie(http);
            URL u = new URL(http);
            HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
            urlConnection.setRequestProperty("Cookie", cookies1);
            String id = Settings.Secure.ANDROID_ID;
            Log.d("id", id);
            urlConnection.setRequestProperty("deviceid", id);


            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            JSONObject input = placeFacade.placeFacadeJson();
            Log.d("a", input.toString());
            writer.write(input.toString());
            writer.flush();
            writer.close();Log.d("erCode", String.valueOf(urlConnection.getResponseCode()));

            InputStream is = urlConnection.getInputStream();
            rd = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }
            urlConnection.disconnect();

            json1 = sb.toString();
            Log.d("pickUpPlace", json1);
        }



            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return json1;
    }
}
