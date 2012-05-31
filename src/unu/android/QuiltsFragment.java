package unu.android;

import unu.rest.AuthenticationException;
import unu.rest.PatchList;
import unu.rest.UnuClient;
import android.content.Intent;

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
    for (int i = 0; i < patchList.length(); i++) {
      this.addGroup(patchList.get(i));
    }
  }
}
