package unu.rest;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Patch extends UnuClient {
	/** Get scraps in patch (GET) */
	protected URL patchScrapsEndpoint;
	
	protected JSONObject json;
	protected JSONArray contents;
	
	protected JSONArray getContents() throws AuthenticationException {
		if (contents == null) {
			Response response = get(patchScrapsEndpoint);
			if (response.getStatusCode() != HttpURLConnection.HTTP_OK) {
				throw new AuthenticationException();
			}
			
			try {
				contents = new JSONArray(response.getContent());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return contents;
	}
	
	public String getTitle() {
		try {
			return json.getString("title");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String getDescription() {
		try {
			return json.getString("description");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public int getId() {
		try {
			return json.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<Scrap> getScraps() throws AuthenticationException {
		ArrayList<Scrap> list = new ArrayList<Scrap>(getContents().length());
		for (int i = 0; i < getContents().length(); i++) {
			try {
				list.add(new Scrap(getContents().getString(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<String> getBodies() throws AuthenticationException {
		List<Scrap> scraps = getScraps();
		ArrayList<String> bodies = new ArrayList<String>(scraps.size());
		for (Scrap scrap : scraps) {
			bodies.add(scrap.getBody());
		}
		return bodies;
	}
	
	public Patch(String json) {
		try {
			this.json = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
			this.json = new JSONObject();
		}
		
		try {
			patchScrapsEndpoint = new URL(UnuClient.root, "/users/" + userId + "/patches/" + getId() + ".json");
		}
		catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in Patch", e);
		}
	}
}
