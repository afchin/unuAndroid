package unu.android;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;


public class ImageFragment extends Fragment {

  private ViewPagerAdapter mAdapter;
  private ViewPager mPager;
  private View currView;
  ArrayList<String> embeds = new ArrayList<String>();

  public ImageFragment(ArrayList<String> embeds){
    this.embeds = embeds;
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    currView = inflater.inflate(R.layout.inbox_frag_layout, container, false);

    this.mAdapter  = new ViewPagerAdapter(getActivity(), embeds);
    mPager = (ViewPager)currView.findViewById(R.id.viewpager);
    mPager.setAdapter(mAdapter);

    new setAdapterTask().execute();

    return currView;
  }

  private class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<View> views;
    private ArrayList<String> embeds;

    public ViewPagerAdapter(Context context, ArrayList<String> embeds) {
      views = new ArrayList<View>();
      this.embeds = embeds;
      for (String url: embeds){  
        final String mimeType = "text/html";
        final String encoding = "utf-8";
        WebView webview = new WebView(context);

        String nBody = "<html><head></head><body>" + url + "</body></html>" ;

        webview.setInitialScale(100);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setPluginsEnabled(true);
        webview.getSettings().setPluginState(PluginState.ON);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setBackgroundColor(0x00000000);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setScrollbarFadingEnabled(false);

        webview.loadDataWithBaseURL("http://www.youtube.com", nBody, mimeType, encoding, null);

        views.add(webview);

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

}
