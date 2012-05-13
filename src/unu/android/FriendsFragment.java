package unu.android;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FriendsFragment extends Fragment{
  private static final String ROW_SPACE = "     ";
  private HashMap<String, String> mapFriendInfo = new HashMap<String, String>();
  private TableLayout table;
  private final String mimeType = "text/html";
  private final String encoding = "utf-8";
  private final String DEFAULT_ICON_SRC = "<img src = \"http://i.imgur.com/oTniP.png\" >";
  
  public FriendsFragment(HashMap<String, String> mFriendInfo){
    this.mapFriendInfo = mFriendInfo;
  }
  
  public FriendsFragment(){
    for (int i = 0 ; i < 25; i++){
      this.mapFriendInfo.put("testFriend" + ((Integer) i).toString() + ROW_SPACE, "testStatus");
    }

  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    View currView = inflater.inflate(R.layout.friends_layout, container, false);
    table = (TableLayout) currView.findViewById(R.id.table);
    
    Iterator<Map.Entry<String, String>> it = mapFriendInfo.entrySet().iterator();
    
    addRow("Friend" + ROW_SPACE, "Status");
    addSpacer();
//    addRow(null, "icontest", "icontest");
//    addRow("<img src = \"http://i.imgur.com/nOHej.gif\" >", "icontest2", "icontest2");
    while (it.hasNext()){
      Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
      addRow((String)pairs.getKey(), (String)pairs.getValue());

      addSpacer();
    }
    return currView;
  }
  
  private void addSpacer() {
    TextView spacer = new TextView(this.getActivity());
    spacer.setHeight(5);
    
    table.addView(spacer);
  }
  
  private void addRow(String iString, String fString, String sString){
    TableRow row = new TableRow(this.getActivity());
    
    WebView icon = new WebView(this.getActivity());
    if (iString == null){
      icon.loadDataWithBaseURL("", DEFAULT_ICON_SRC, mimeType, encoding, null); 
    } else if (iString == "titleBar"){
      // leave it null for the title
    } else {
      icon.loadDataWithBaseURL("", iString, mimeType, encoding, null);
    }
    icon.setBackgroundColor(0x00000000);
    TextView friend = new TextView(this.getActivity());
    friend.setText(fString);
    friend.setTextColor(Color.WHITE);
    
    TextView status = new TextView(this.getActivity());
    status.setText(sString);
    
    row.addView(icon);
    row.addView(friend);
    row.addView(status);
    
    table.addView(row);    
  }
  
  private void addRow(String fString, String sString) {
    TableRow row = new TableRow(this.getActivity());
    
    TextView friend = new TextView(this.getActivity());
    friend.setText(fString);
    friend.setTextColor(Color.WHITE);
    
    TextView status = new TextView(this.getActivity());
    status.setText(sString);
    
    row.addView(friend);
    row.addView(status);
    
    table.addView(row);
  }
}
