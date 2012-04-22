package unu.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class InboxFragment extends Fragment {

  private ViewPagerAdapter mAdapter;
  private ViewPager mPager;
  ArrayList<String> fragmentURLs = new ArrayList<String>();
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    View currView = inflater.inflate(R.layout.inbox_frag_layout, container, false);
    
    List<Fragment> fragments = new Vector<Fragment>();
    
      fragmentURLs.add("http://i.imgur.com/Svphn.jpg");
      fragmentURLs.add("http://i.imgur.com/NZpzV.jpg");
      fragmentURLs.add("http://i.imgur.com/H6XOr.gif");
      fragmentURLs.add("http://i.imgur.com/ZaXs2.gif");
      fragmentURLs.add("http://lemon.ly/wp-content/uploads/2012/02/Jaspersoft-Infographic_final.jpg");      
      
    
    this.mAdapter  = new ViewPagerAdapter(getActivity(), fragmentURLs);
    mPager = (ViewPager)currView.findViewById(R.id.viewpager);
    
    new setAdapterTask().execute();
    
    return currView;
  }
  
  private class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<WebView> views;
    private ArrayList<String> srcURLs;

    public ViewPagerAdapter(Context context, ArrayList<String> srcURLs) {
      views = new ArrayList<WebView>();
      this.srcURLs = srcURLs;
      for (String url: srcURLs){
//        View inflatedView = View.inflate(context, R.layout.img_frag_layout, null);
//        WebView webview = (WebView) inflatedView.findViewById(R.id.webView);
        WebView webview = new WebView(context);
        
//        webview.clearView();
//        webview.clearCache(true);
//        webview.getSettings().setBuiltInZoomControls(true);
//        webview.getSettings().setSupportZoom(true);
//        webview.getSettings().setLoadWithOverviewMode(true);
//        webview.getSettings().setUseWideViewPort(true);
        
//        webview.getSettings().setLoadWithOverviewMode(true);
//        webview.getSettings().setUseWideViewPort(true);

        try {
          webview.loadUrl(url);
          webview.setBackgroundColor(0x00000000);
          webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
          views.add(webview);
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    }

      @Override
      public void destroyItem(View view, int arg1, Object object) {
        ((ViewPager) view).removeView((WebView) object);
      }

      @Override
      public void finishUpdate(View arg0) {

      }

      @Override
      public int getCount() {
        return views.size();
      }

      @Override
      public Object instantiateItem(View view, int position) {
        View myView = views.get(position);
        
        ((ViewPager) view).addView(myView);
        return myView;
      }

      @Override
      public boolean isViewFromObject(View view, Object object) {
        return view == object;
      }

      @Override
      public void restoreState(Parcelable arg0, ClassLoader arg1) {

      }

      @Override
      public Parcelable saveState() {
        return null;
      }

      @Override
      public void startUpdate(View arg0) {

      }

    }
  private class setAdapterTask extends AsyncTask<Void,Void,Void>{
    protected Void doInBackground(Void... params) {
          return null;
      }

      @Override
      protected void onPostExecute(Void result) {
          mPager.setAdapter(mAdapter);
      }
}
  
  public static class MyFragment extends Fragment {
    static String srcURL;

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
    
    static String getSrcUrl(){
      return srcURL;
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
