package unu.rest;

import java.net.MalformedURLException;
import java.net.URL;

public class Inbox extends Patch {

	@Override
	public String getTitle() {
		return "Inbox";
	}
	
	@Override
	public String getDescription() {
		return "User's inbox";
	}
	
	public Inbox(String json) {
		super(json);
		
		try {
			// Get scraps in inbox {@code inboxId} (GET)
			patchScrapsEndpoint = new URL(UnuClient.root, "/inbox/" + getId() + ".json");
		}
		catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in Patch", e);
		}
	}
}
