package unu.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import unu.rest.AuthenticationException;
import unu.rest.Patch;
import unu.rest.PatchList;
import unu.rest.UnuClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.LinearLayout;

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

    // start from 1 to exclude the "Profile"
    for (int i = 1; i < patchList.length(); i++) {
      this.addGroup(patchList.get(i));
    }
  }
  
}
