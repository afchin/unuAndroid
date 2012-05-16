package unu.android;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class UnuActivity extends FragmentActivity implements TabHost.OnTabChangeListener {
	private TabHost mTabHost;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();
	private TabInfo mLastTab = null;

	private class TabInfo {
		private String tag; 
		private Class<?> clss;
		private Bundle args;
		private Fragment fragment;
		TabInfo(String tag, Class<?> clss, Bundle args){
			this.tag = tag;
			this.clss = clss;
			this.args = args;
		}
	}

	class TabFactory implements TabContentFactory {
		private final Context mContext;

		public TabFactory(Context context){
			mContext = context;
		}

		public View createTabContent(String tag){
			View v = new View(mContext);
			v.setMinimumHeight(0);
			v.setMinimumWidth(0);
			return v;
		}
	}

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tabs_layout);

		initializeTabHost(savedInstanceState);
		if (savedInstanceState != null){
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
	}

	protected void onSaveInstanceState(Bundle outState){
		outState.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(outState);
	}

	private void initializeTabHost(Bundle args){
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup();

    addTab("Profile", ProfileFragment.class, R.drawable.view_scrap, args);
		addTab("Inbox", InboxFragment.class, R.drawable.ic_tab_inbox, args);
		addTab("Patches", PatchesFragment.class, R.drawable.ic_tab_patches, args);
		addTab("Quilts", QuiltsFragment.class, R.drawable.ic_tab_quilts, args);
		addTab("Basket", BasketFragment.class, R.drawable.ic_tab_basket, args);
		addTab("Browser", WebFragment.class, R.drawable.view_scrap, args);
    addTab("Friends", FriendsFragment.class, R.drawable.view_scrap, args);
    

		this.onTabChanged("Profile");
		mTabHost.setOnTabChangedListener(this);
		
		
		Display display = getWindowManager().getDefaultDisplay(); 
		int width = display.getWidth();  // deprecated
		int height = display.getHeight();  // deprecated
		
		for (int i = 0; i < mapTabInfo.size(); i++){
//		   mTabHost.getTabWidget().getChildAt(i).setLayoutParams(new 
//		        LinearLayout.LayoutParams(150, 100));  
//		  mTabHost.getTabWidget().getChildAt(i).getLayoutParams().width = (int) 
//		      (width/4);
		  if (width/4 > 200) {
	      mTabHost.getTabWidget().getChildAt(i).getLayoutParams().width = (int) 
          (width/4);	    
		  } else {
        mTabHost.getTabWidget().getChildAt(i).getLayoutParams().width = 175;     		    
		  }

		}


	}

	private void addTab(String name, Class<?> fragment, int resId, Bundle args) {
		TabInfo tabInfo = new TabInfo(name, fragment, args);

		TabHost.TabSpec tabSpec = mTabHost.newTabSpec(name).setIndicator("",
                getResources().getDrawable(resId));
		tabSpec.setContent(new TabFactory(this));
		
		tabInfo.fragment = getSupportFragmentManager().findFragmentByTag(tabSpec.getTag());
		if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()){
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.detach(tabInfo.fragment);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
		}

		mTabHost.addTab(tabSpec);
		
		mapTabInfo.put(tabInfo.tag, tabInfo);
	}

	public void onTabChanged(String tag){
		TabInfo newTab = this.mapTabInfo.get(tag);
		if (mLastTab != newTab) {
			FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
			if (mLastTab != null){
				if (mLastTab.fragment != null){
					if ((mLastTab.tag == "Quilts") || (mLastTab.tag == "Patches")){
					  FragmentManager fm = this.getSupportFragmentManager();
					  // right now, need this because of disappearing fragment problem
					    fm.popBackStack();
					}
	         ft.detach(mLastTab.fragment);
				}
			}
			if (newTab != null) {
				if (newTab.fragment == null){
					newTab.fragment = Fragment.instantiate(this,
							newTab.clss.getName(), newTab.args);
					ft.replace(R.id.realtabcontent, newTab.fragment, newTab.tag);
				} else {
					ft.attach(newTab.fragment);
				}
			}
			mLastTab = newTab;
			ft.commit();
			this.getSupportFragmentManager().executePendingTransactions();
		}
	}
}