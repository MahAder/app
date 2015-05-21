package ader.getout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import static ader.getout.ApiClient.*;

/**
 * Created by User on 01.05.2015.
 */
public class DevTools extends FragmentActivity {

    ApiClient api = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_tools);

        api = getInstance();

        final WebView mWebView = (WebView) findViewById(R.id.activity_main_webview);
        Button info = (Button) findViewById(R.id.button);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                api.userInfo(mWebView);
            }
        });

        Button map = (Button) findViewById(R.id.button2);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevTools.this, MapActivity.class);

                startActivity(intent);
            }
        });

    }

}
