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

public class GroupListFragment extends ListFragment {

  private Fragment topFragment;
  private ArrayList<String> values = new ArrayList<String>();
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  
  protected void addGroup(String group){
    this.values.add(group);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_1, values);
//    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//        android.R.layout.list_item, values);
    setListAdapter(adapter);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {

    ArrayList<String> embeds = new ArrayList<String>();
//    embeds.add("<img src = \"http://i.imgur.com/s0mKE.gif\" >");
    embeds.add("<img src = \"http://i.imgur.com/NZpzV.jpg\" >");
    embeds.add("<img src = \"http://i.imgur.com/1Mrxj.jpg\" >");
    embeds.add("<img src = \"http://i.imgur.com/G6P8J.jpg\" >");
    
    topFragment = new ContentViewerFragment(embeds);
    FragmentTransaction transaction = getFragmentManager().beginTransaction();

    transaction.replace(R.id.realtabcontent, topFragment);
//    transaction.addToBackStack(null);
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
  
//  public void popTopFragment(){
//    if (topFragment != null){
//      FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//      transaction.addToBackStack(topFragment.getClass().toString());
////      transaction.addToBackStack(null);
////      transaction.addToBackStack(this.getClass().toString());
//      transaction.commit();
//    }
//  }
//  
//  public void addTopFragment(){
//    if (topFragment != null){
//      FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//      transaction.replace(R.id.realtabcontent, topFragment);
//      transaction.addToBackStack(null);
////      transaction.addToBackStack(this.getClass().toString());
//      transaction.commit();
//    }
//  }
}
