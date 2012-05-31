package unu.rest;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Alexandre Boulgakov
 */
public class Basket extends Patch {
	@Override
	public String getTitle() {
		return "Basket";
	}
	
	@Override
	public String getDescription() {
		return "User's basket";
	}
	
	@Override
	/* TODO: Get basket ID instead of placeholder */
	public int getId() {
		return 0;
	}
	
	public Basket() {
		super("");
		
		try {
			// Get scraps in basket (GET)
			patchScrapsEndpoint = new URL(UnuClient.root, "/basket/" + getId() + ".json");
		}
		catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in Basket", e);
		}
	}
}
