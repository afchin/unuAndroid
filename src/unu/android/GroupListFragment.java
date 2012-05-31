package unu.android;

import java.util.ArrayList;
import java.util.List;

import unu.rest.AuthenticationException;
import unu.rest.Patch;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupListFragment extends ListFragment {

  private Fragment topFragment;
  private List<String> values = new ArrayList<String>();
  private List<Patch> patches = new ArrayList<Patch>();
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  
  protected void addGroup(Patch patch){
    this.patches.add(patch);
    this.values.add(patch.getTitle());
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

    List<String> embeds = new ArrayList<String>();
    try {
      embeds = patches.get(position).getBodies();
    } catch (AuthenticationException e) {
      // TODO Auto-generated catch block
      // show the error image
      embeds.add("<img src = \"http://i.imgur.com/MTdDt.png\" >");
    }

    
    topFragment = new ContentViewerFragment(embeds);
    FragmentTransaction transaction = getFragmentManager().beginTransaction();

    transaction.replace(R.id.realtabcontent, topFragment);
    transaction.addToBackStack(null);
//    transaction.addToBackStack(this.getClass().toString());
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
