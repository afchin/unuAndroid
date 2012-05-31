package unu.android;

import java.util.List;

import unu.rest.AuthenticationException;
import unu.rest.UnuClient;
import android.content.Intent;

/**
 * @author Alexandre Boulgakov
 */
public class BasketFragment extends ContentViewerFragment {
	List<String> embeds;

	public BasketFragment() {
		super();
		try {
			embeds = UnuClient.getBasket().getBodies();
		} catch (AuthenticationException e) {
			Intent i = new Intent(this.getActivity().getApplicationContext(),
					LoginActivity.class);
			startActivity(i);
		}

		for (String embed : embeds) {
			this.addEmbed(embed);
		}
	}
}
