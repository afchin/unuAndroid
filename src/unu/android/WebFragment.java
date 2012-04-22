package unu.android;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.webkit.WebView;

public class WebFragment extends Fragment{
  WebView loadWeb;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (container == null){
      return null;
    } else {
      return (LinearLayout)inflater.inflate(R.layout.patches_frag_layout, container, false);
    }
  }
}
