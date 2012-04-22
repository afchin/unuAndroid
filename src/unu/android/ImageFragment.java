package unu.android;

import java.net.URL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class ImageFragment extends Fragment {
  String srcURL;
  
  static ImageFragment newInstance(String url) {
      ImageFragment f = new ImageFragment();
      Bundle b = new Bundle();
      b.putString("srcurl", url);
      f.setArguments(b);
      return f;
  }
  
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Bundle args = getArguments();
    if (args != null) {
        srcURL = args.getString("srcurl", srcURL);
    }
}
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {

  View V = inflater.inflate(R.layout.img_frag_layout, container, false);
  WebView webview = (WebView)V.findViewById(R.id.webView);
  webview.getSettings().setLoadWithOverviewMode(true);
  webview.getSettings().setUseWideViewPort(true);

  try {
    webview.loadUrl(this.srcURL);
  } catch (Exception e) {
    e.printStackTrace();
}
  
  return V;

  }


}
