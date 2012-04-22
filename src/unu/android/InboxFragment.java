package unu.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class InboxFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {


    //    scroller.addView(text);
    //    scroller.addView(image);
    //    text.setText("Inbox test");
    View currview = inflater.inflate(R.layout.inbox_frag_layout, container, false);

    return  currview;


    //    if (container == null){
    //      return null;
    //    } else {
    //      ImageView i = null;
    //      try {
    //        i = (ImageView)getView().findViewById(R.id.image);;
    //        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://i.imgur.com/ZaXs2.gif").getContent());
    //        i.setImageBitmap(bitmap); 
    //      } catch (MalformedURLException e) {
    //        e.printStackTrace();
    //      } catch (IOException e) {
    //        e.printStackTrace();
    //      }
    //
    //      return i;
    //      ScrollView scroller = new ScrollView(getActivity());
    //      TextView text = new TextView(getActivity());
    //      int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
    //              4, getActivity().getResources().getDisplayMetrics());
    //      text.setPadding(padding, padding, padding, padding);
    //      scroller.addView(text);
    //      text.setText("Inbox test");
    //      return scroller;
    //      return (LinearLayout)inflater.inflate(R.layout.inbox_frag_layout, container, false);
    //    }
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    WebView image = (WebView) getView().findViewById(R.id.webview);
    image.loadUrl("http://i.imgur.com/Svphn.jpg");
    super.onViewCreated(view, savedInstanceState);
  }

}
