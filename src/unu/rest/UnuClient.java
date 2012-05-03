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
	
	/** True if the user is currently logged in.
	 * This value can be checked before issuing requests that require the user to be logged in. 
	 */
	protected boolean loggedIn = false;
	/** Checks if the user is currently logged in.
	 * @return True if the user is logged in
	 */
	public boolean isLoggedIn() {
		// TODO: Query unu to check if user is logged in instead of dead-reckoning
		return loggedIn;
	}
	
	/** Initialize all endpoints */
	static {
		try {
			root = new URL("http://www.unu.fm");
			loginEndpoint = new URL(root, "/users/login");
			createScrapEndpoint = new URL(root, "/inbox_scraps");
		} catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in UnuClient", e);
		}
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
		Response response = post(loginEndpoint, params);
		/* The server responds with HTTP_MOVED_TEMP on log in to redirect users to the home page.
		 * If the credentials are rejected, the server responds with an HTTP_OK. */
		loggedIn = response.getStatusCode() == HttpURLConnection.HTTP_MOVED_TEMP;
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
		if (!isLoggedIn()) {
			throw new AuthenticationException();
		}
		HashMap<String, String> params = new HashMap<String, String>(2);
		params.put(inboxScrapBody, body);
		params.put(inboxScrapSource, source);
		post(createScrapEndpoint, params);
	}
}
