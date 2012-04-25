package unu.android;

import java.util.ArrayList;

import android.app.Activity;
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

public class PatchesFragment extends ListFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2" };
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    
    ArrayList<String> embeds = new ArrayList<String>();
    embeds.add("<img src = \"http://i.imgur.com/Svphn.jpg\" >");
    Fragment newFragment = new ContentViewerFragment(embeds);
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    
    transaction.replace(R.id.realtabcontent, newFragment);
    transaction.addToBackStack(this.getClass().toString());
    transaction.commit();
    
//    String item = (String) getListAdapter().getItem(position);
//    DetailFragment fragment = (DetailFragment) getFragmentManager()
//        .findFragmentById(R.id.detailFragment);
//    if (fragment != null && fragment.isInLayout()) {
//      fragment.setText(item);
//    } else {
//      Intent intent = new Intent(getActivity().getApplicationContext(),
//          DetailActivity.class);
//      intent.putExtra("value", item);
//      startActivity(intent);
//
//    }

  }
}
