package unu.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alexandre Boulgakov
 */
public class Friendships extends UnuClient {
	private List<Friend> friends;
	private List<Friend> incoming;
	private List<Friend> outgoing;
	
	public Friendships(String json) {
		JSONObject friendships = null;
		try {
			friendships = new JSONObject(json); 
		} catch (JSONException e) {
			e.printStackTrace();
		}
		friends = toFriendList(friendships.optJSONArray("current"));
		incoming = toFriendList(friendships.optJSONArray("requested"));
		outgoing = toFriendList(friendships.optJSONArray("pending"));
	}
	
	private List<Friend> toFriendList(JSONArray array) {
		if (array == null) {
			return Collections.emptyList();
		}
		List<Friend> result = new ArrayList<Friend>(array.length());
		for (int i = 0; i < array.length(); i++) {
			result.add(new Friend(array.optJSONObject(i)));
		}
		return Collections.unmodifiableList(result);
	}
	
	/** Current friends */
	public List<Friend> getFriends() {
		return friends;
	}
	
	/** Outgoing friend requests */
	public List<Friend> getPendingFriends() {
		return outgoing;
	}
	
	/** Incoming friend requests */
	public List<Friend> getRequestedFriends() {
		return incoming;
	}
}
