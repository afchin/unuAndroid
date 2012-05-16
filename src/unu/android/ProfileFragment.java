package unu.android;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
  private static final String ROW_SPACE = "     ";
  private TableLayout friendRequests;
  private TableLayout pendingFriendRequests;
  private TableLayout friends;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    View currView = inflater.inflate(R.layout.profile_layout, container, false);
    
    friendRequests = (TableLayout) currView.findViewById(R.id.friendRequests);
    pendingFriendRequests = (TableLayout) currView.findViewById(R.id.pendingFriendRequests);
    friends = (TableLayout) currView.findViewById(R.id.friends);
    
    addRow(friendRequests, "friend1", "pending");
    
    addRow(pendingFriendRequests, "friend2", "pending");
    
    addRow(friends, "friend3", "View Profile");
    addRow(friends, "friend4", "View Profile");
    return currView;
  }
  
  private void addRow(TableLayout targetTable, String fString, String sString) {
    TableRow row = new TableRow(this.getActivity());
    fString += ROW_SPACE;
    TextView friend = new TextView(this.getActivity());
    friend.setText(fString);
    friend.setTextColor(Color.WHITE);
    
    TextView status = new TextView(this.getActivity());
    status.setText(sString);
    
    row.addView(friend);
    row.addView(status);
    
    targetTable.addView(row);
    addSpacer(targetTable);
  }
  
  private void addSpacer(TableLayout targetTable) {
    TextView spacer = new TextView(this.getActivity());
    spacer.setHeight(5);
    
    targetTable.addView(spacer);
  }
}
