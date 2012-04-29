package unu.rest;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class UnuClient extends RestClient {
	private static final URL root;
	private static final URL loginEndpoint;
	
	static {
		try {
			root = new URL("http://www.unu.fm");
			loginEndpoint = new URL(root, "/users/login");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid endpoints in UnuClient", e);
		}
	}
	
	
	private static final String loginUsername = "user[email]";
	private static final String loginPassword = "user[password]";
	private static final String loginMethod = "POST";
	public boolean logIn(String username, String password) {
		HashMap<String, String> params = new HashMap<String, String>(2);
		params.put(loginUsername, username);
		params.put(loginPassword, password);
		Response<Object> response = submitRequest(loginEndpoint, loginMethod, params);
		return response.getStatusCode() == HttpURLConnection.HTTP_MOVED_TEMP;
	}
}
