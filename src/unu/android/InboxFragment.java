package unu.android;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class InboxFragment extends Fragment {
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (container == null){
      return null;
    } else {
      ScrollView scroller = new ScrollView(getActivity());
      TextView text = new TextView(getActivity());
      int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
              4, getActivity().getResources().getDisplayMetrics());
      text.setPadding(padding, padding, padding, padding);
      scroller.addView(text);
      text.setText("Inbox test");
      return scroller;
//      return (LinearLayout)inflater.inflate(R.layout.inbox_frag_layout, container, false);
    }
  }
}
