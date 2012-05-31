package unu.android;

import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import unu.rest.AuthenticationException;
import unu.rest.UnuClient;
import android.content.Intent;
import unu.rest.Friendships;
import unu.rest.Friend;

public class ProfileFragment extends Fragment {
  private static final String ROW_SPACE = "     ";
  private TableLayout friendRequests; //requested friends
  private TableLayout pendingFriendRequests;
  private TableLayout friends;
  private Friendships friendships;

  public ProfileFragment() {
    super();
    try {
      friendships = UnuClient.getFriendships();
    } catch (AuthenticationException e) {
      Intent i = new Intent(this.getActivity().getApplicationContext(),
          LoginActivity.class);
      startActivity(i);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    View currView = inflater.inflate(R.layout.profile_layout, container, false);

    friendRequests = (TableLayout) currView.findViewById(R.id.friendRequests);
    pendingFriendRequests = (TableLayout) currView.findViewById(R.id.pendingFriendRequests);
    friends = (TableLayout) currView.findViewById(R.id.friends);

    List<Friend> currFriends = friendships.getFriends();
    List<Friend> pendingFriends = friendships.getPendingFriends();
    List<Friend> requestedFriends = friendships.getRequestedFriends();

    if (currFriends.isEmpty()) {
      addEmpty(friends);      
    } else {
      for (Friend f: currFriends) {
        //addRow(friends, f.getUserName(), "View Profile");  
        addRow(friends, f.getUserName(), "");  
      }
    }

    if (pendingFriends.isEmpty()){
      addEmpty(pendingFriendRequests);   
    } else {
      for (Friend f: pendingFriends){
        //addRow(pendingFriendRequests, "friend2", "pending");  
        addRow(pendingFriendRequests, f.getUserName(), "");
      }      
    }

    if (requestedFriends.isEmpty()){
      addEmpty(friendRequests);   
    } else {
      for (Friend f: requestedFriends){
        addRow(friendRequests, f.getUserName(), "");     
      }
    }

    return currView;
  }

  private void addEmpty(TableLayout targetTable){
    TableRow row = new TableRow(this.getActivity());   
    TextView none = new TextView(this.getActivity());
    none.setText("None");
    none.setTextColor(Color.GRAY);

    row.addView(none);
    targetTable.addView(row);
    addSpacer(targetTable);
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
