package unu.android;

import java.util.List;

import android.content.Intent;

import unu.rest.AuthenticationException;
import unu.rest.UnuClient;

public class InboxFragment extends ContentViewerFragment {

	List<String> embeds;

	public InboxFragment() {
		super();
		try {
			embeds = UnuClient.getInbox().getBodies();
		} catch (AuthenticationException e) {
			Intent i = new Intent(this.getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(i);
		}

		for (String embed : embeds) {
			this.addEmbed(embed);
		}
	}
}
