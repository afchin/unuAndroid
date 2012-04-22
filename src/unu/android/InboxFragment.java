package unu.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class InboxFragment extends Fragment {

  private ViewPagerAdapter mAdapter;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View currView = inflater.inflate(R.layout.inbox_frag_layout, container, false);
    ViewPager mViewPager = (ViewPager)currView.findViewById(R.id.viewpager);
    
    mAdapter = new ViewPagerAdapter(getFragmentManager(), null);
    
    return currView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    List<Fragment> fragments = new Vector<Fragment>();
    ArrayList<String> fragmentURLs = new ArrayList<String>();
    
    fragmentURLs.add("http://i.imgur.com/Svphn.jpg");
    fragmentURLs.add("http://i.imgur.com/NZpzV.jpg");
    fragmentURLs.add("http://i.imgur.com/H6XOr.gif");
    
    for (String url : fragmentURLs){
      Fragment newFragment = MyFragment.newInstance(url);
      fragments.add(newFragment);
    }
    
    this.mAdapter  = new ViewPagerAdapter(getFragmentManager(), fragments);
    ViewPager pager = (ViewPager)getView().findViewById(R.id.viewpager);
    pager.setAdapter(this.mAdapter);
  }
  
  public static class MyFragment extends Fragment {
    String srcURL;

    /**
     * Create a new instance of MyFragment that will be initialized
     * with the given arguments.
     */
    static MyFragment newInstance(String srcurl) {
        MyFragment f = new MyFragment();
        Bundle b = new Bundle();
        b.putString("srcurl", srcurl);
        f.setArguments(b);
        return f;
    }

    /**
     * During creation, if arguments have been supplied to the fragment
     * then parse those out.
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            String srcurl = args.getString("srcurl");
            if (srcurl != null) {
                srcURL = srcurl;
            }
        }
    }

    /**
     * Create the view for this fragment, using the arguments given to it.
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.img_frag_layout, container, false);
        WebView webview = (WebView)V.findViewById(R.id.webView);
        webview.loadUrl(this.srcURL);
        webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        return V;
    }
}

}
