package unu.android;

import unu.rest.AuthenticationException;
import unu.rest.PatchList;
import unu.rest.UnuClient;
import android.content.Intent;

public class PatchesFragment extends GroupListFragment {

  PatchList patchList;

  
  public PatchesFragment() {
    super();
    try {
      patchList = UnuClient.listPatches();
    } catch (AuthenticationException e) {
      Intent i = new Intent(this.getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(i);
    }

    for (int i = 0; i < patchList.length(); i++) {
      this.addGroup(patchList.get(i));
    }
  }
  
}
