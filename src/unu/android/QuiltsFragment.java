package unu.android;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

public class QuiltsFragment extends GroupListFragment {
  public QuiltsFragment(){
    super();
    this.addGroup("Quilt1");
    this.addGroup("Quilt2");
  }
}
