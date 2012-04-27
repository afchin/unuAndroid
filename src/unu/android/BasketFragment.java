package unu.android;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

public class BasketFragment extends ContentViewerFragment {
  ArrayList<String> embeds = new ArrayList<String>();
  
  public BasketFragment(){
    super();
    this.addEmbed("<img src = \"http://i.imgur.com/f0nio.gif\" >");
    this.addEmbed("<img src = \"http://i.imgur.com/NZpzV.jpg\" >");
  }
}
