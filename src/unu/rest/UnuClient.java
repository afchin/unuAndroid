package unu.rest;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/** Provides common methods to access the unu REST server.
 * @author Alexandre Boulgakov
 */
public class UnuClient extends RestClient {
	/* ENDPOINTS. These are initialized in a static initializer since they
	 * can throw a checked exception (MalformedURLException).
	 */
	/** The unu REST server root */
	private static final URL root;
	/** Log in (POST) */
	private static final URL loginEndpoint;
	/** Create a new inbox scrap (POST) */
	private static final URL createScrapEndpoint;
	/** Get current user ID (GET) */
	private static final URL getUidEndpoint;
	
	/** Initialize all endpoints */
	static {
		try {
			root = new URL("http://www.unu.fm");
			loginEndpoint = new URL(root, "/users/login");
			createScrapEndpoint = new URL(root, "/inbox_scraps");
			getUidEndpoint = new URL(root, "/users/me.json");
		} catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in UnuClient", e);
		}
	}
	
	/** Current user's ID or 0 if no user is logged in */
	protected int userId = 0;
	/** Checks if the user logged in using the cached user ID.
	 * @return True if the user is logged in
	 */
	public boolean isLoggedInCached() {
		return userId != 0;
	}
	
	/** Checks if the user is currently logged in.
	 * @return True if the user is logged in
	 */
	public boolean isLoggedIn() {
		Response response = get(getUidEndpoint);
		if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
			userId = Integer.parseInt(response.getContent());
		} else {
			// TODO: This is a temporary plug for a backend bug
			userId = 0;
		}
		
		return isLoggedInCached();
	}
	
	/** Name of the username parameter for the log-in request */
	private static final String loginUsername = "user[email]";
	/** Name of the password parameter for the log-in request */
	private static final String loginPassword = "user[password]";
	/** Attempt to log in the user with the specified credentials.
	 * @param username User's username or email address
	 * @param password User's password
	 * @return True if the user was successfully logged in
	 */
	public boolean logIn(String username, String password) {
		HashMap<String, String> params = new HashMap<String, String>(2);
		params.put(loginUsername, username);
		params.put(loginPassword, password);
		post(loginEndpoint, params);
		return isLoggedIn();
	}
	
	/** Name of the body parameter for the create-scrap request */
	private static final String inboxScrapBody = "inbox_scrap[body]";
	/** Name of the source parameter for the create-scrap request */
	private static final String inboxScrapSource = "inbox_scrap[source]";
	/** Posts a new scrap to the user's inbox. 
	 * @param body TODO
	 * @param source TODO
	 * @throws AuthenticationException Thrown if the user is not logged in
	 */
	public void postContent(String body, String source) throws AuthenticationException {
		if (!isLoggedInCached() && !isLoggedIn()) {
			throw new AuthenticationException();
		}
		HashMap<String, String> params = new HashMap<String, String>(2);
		params.put(inboxScrapBody, body);
		params.put(inboxScrapSource, source);
		post(createScrapEndpoint, params);
	}
}
