package unu.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PatchesActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    TextView textview = new TextView(this);
    textview.setText("This is the Patches tab");
    setContentView(textview);
  }
}
