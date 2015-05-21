package ader.getout;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    private Button but;
    private boolean finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.button10);
        finish = false;
        init();
    }

    private void init() {
        Button login = (Button) findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WebView webView = (WebView) findViewById(R.id.webView);
                final String url = "https://ec2-54-175-242-149.compute-1.amazonaws.com/auth/facebook";
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setDomStorageEnabled(true);
                webView.loadUrl(url);
                // mWebView.loadUrl("https://getout2go.com/auth/facebook");
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                        super.onReceivedSslError(view, handler, error);
                        Log.d("ssl", error.toString());
                        // this will ignore the Ssl error and will go forward to your site
                        //mWebView.getSettings().setAllowContentAccess(true);
                        but.setVisibility(View.VISIBLE);
                        but.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                handler.proceed();

                                webView.loadUrl(url);
                                but.setVisibility(View.INVISIBLE);
                                finish = true;
                            }
                        });
                    }

                    public void onPageFinished(WebView view, String url) {
                    if(finish) {
                        String host = Uri.parse(url).getHost();
                        if (host.equals("ec2-54-175-242-149.compute-1.amazonaws.com")) {
                            webView.setVisibility(View.INVISIBLE);
                            but.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(MainActivity.this, MapActivity.class);

                            startActivity(intent);
                        } else {
                            webView.setVisibility(View.VISIBLE);
                        }
                    }
                    }
                });
            }
        });
    }
}
