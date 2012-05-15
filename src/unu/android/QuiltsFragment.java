package unu.android;

import unu.rest.AuthenticationException;
import unu.rest.PatchList;
import unu.rest.UnuClient;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

public class QuiltsFragment extends GroupListFragment {
  
  // just copied the patch code for now
  PatchList patchList;
  
  public QuiltsFragment(){
    super();
    try {
      patchList = UnuClient.listPatches();
    } catch (AuthenticationException e) {
      Intent i = new Intent(this.getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(i);
    }

    // start from 1 to exclude the "Profile"
    for (int i = 1; i < patchList.length(); i++) {
      this.addGroup(patchList.get(i));
    }
  }
}
