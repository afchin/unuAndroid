package unu.android;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class UnuActivity extends TabActivity {

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Resources res = getResources(); // Resource object to get Drawables
    TabHost tabHost = getTabHost();  // The activity TabHost
    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
    Intent intent;  // Reusable Intent for each tab

    // Create an Intent to launch an Activity for the tab (to be reused)
    intent = new Intent().setClass(this, InboxActivity.class);

    // Initialize a TabSpec for each tab and add it to the TabHost
    spec = tabHost.newTabSpec("Inbox").setIndicator("Inbox",
        res.getDrawable(R.drawable.ic_tab_inbox))
        .setContent(intent);
    tabHost.addTab(spec);

    // Do the same for the other tabs
    intent = new Intent().setClass(this, PatchesActivity.class);
    spec = tabHost.newTabSpec("Patches").setIndicator("Patches",
        res.getDrawable(R.drawable.ic_tab_patches))
        .setContent(intent);
    tabHost.addTab(spec);
    
    // Do the same for the other tabs
    intent = new Intent().setClass(this, QuiltsActivity.class);
    spec = tabHost.newTabSpec("Quilts").setIndicator("Quilts",
        res.getDrawable(R.drawable.ic_tab_quilts))
        .setContent(intent);
    tabHost.addTab(spec);
    
    // Do the same for the other tabs
    intent = new Intent().setClass(this, BasketActivity.class);
    spec = tabHost.newTabSpec("Basket").setIndicator("Basket",
        res.getDrawable(R.drawable.ic_tab_basket))
        .setContent(intent);
    tabHost.addTab(spec);

    tabHost.setCurrentTab(0);
  }
}