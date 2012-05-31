package unu.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebFragment extends Fragment{
  private WebView lWeb;
  private View currView;
  private EditText tbar;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    currView = inflater.inflate(R.layout.img_frag_layout, container, false);
    tbar = (EditText) currView.findViewById(R.id.toolbar);
    tbar.setText("http://www.google.com");
    lWeb = (WebView) currView.findViewById(R.id.webView);
    Button goBtn = (Button) currView.findViewById(R.id.go);

    goBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        lWeb.loadUrl(tbar.getText().toString());
      }
    });
 
    lWeb.setInitialScale(100);
    lWeb.getSettings().setJavaScriptEnabled(true);
    lWeb.getSettings().setPluginsEnabled(true);
    lWeb.getSettings().setPluginState(PluginState.ON);
    lWeb.getSettings().setLoadWithOverviewMode(true);
    lWeb.getSettings().setBuiltInZoomControls(true);

    lWeb.loadUrl(tbar.getText().toString());

    return currView;
  }
}
