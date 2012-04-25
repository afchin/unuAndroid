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


public class InboxFragment extends ContentViewerFragment {

//  private ViewPagerAdapter mAdapter;
//  private ViewPager mPager;
//  private View currView;
  ArrayList<String> embeds = new ArrayList<String>();

  public InboxFragment(){
    super();
//    this.addEmbed(" <iframe class=\"youtube-player\" type=\"text/html\"" +
//        "src=\"http://www.youtube.com/embed/bIPcobKMB94\" frameborder=\"0\"></iframe>");
    this.addEmbed("<img src = \"http://i.imgur.com/Svphn.jpg\" >");
//    this.addEmbed("<iframe src=\"http://player.vimeo.com/video/39992890\"></iframe>");
    this.addEmbed("<img src=\"http://i.imgur.com/H6XOr.gif\">");
    this.addEmbed("<img src=\"http://i.imgur.com/ZaXs2.gif\">");
////    // use HTML5 embed for soundcloud
//    this.addEmbed("<iframe width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\"" +
//        "src=\"http://w.soundcloud.com/player/?url=http%3A%2F%2Fapi.soundcloud.com%2Ftracks%2F42311114&show_artwork=true\"></iframe>");
  }

}
