package unu.rest;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/** Provides common methods to access the unu REST server.
 * @author Alexandre Boulgakov
 */
public abstract class UnuClient extends RestClient {
	/* ENDPOINTS. These are initialized in a static initializer since they
	 * can throw a checked exception (MalformedURLException).
	 */
	/** The unu REST server root */
	protected static final URL root;
	/** Log in (POST) */
	private static final URL loginEndpoint;
	/** Create a new inbox scrap (POST) */
	private static final URL createScrapEndpoint;
	/** Get current user ID (GET) */
	private static final URL getUidEndpoint;
	/** Get a list of current user's patches (GET)
	 * @return TODO:
	 */
	private static URL listPatchesEndpoint() {
		try {
			return new URL(root, "/users/" + userId + "/patches.json");
		} catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in UnuClient", e);
		}
	}
	/** Get inbox ID (GET) */
	private static final URL inboxIdEndpoint;
	/** Get a list of current user's friends and friend requests (GET) */
	private static URL listFriendshipsEndpoint() {
		try {
			return new URL(root, "/users/" + userId + "/friendships.json");
		} catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in UnuClient", e);
		}
	}
	
	/** Initialize all endpoints */
	static {
		try {
			root = new URL("http://www.unu.fm");
			loginEndpoint = new URL(root, "/users/login");
			createScrapEndpoint = new URL(root, "/inbox_scraps");
			getUidEndpoint = new URL(root, "/users/me.json");
			inboxIdEndpoint = new URL(root, "/inbox.json");
		} catch (MalformedURLException e) {
			// Should not get here since all of the endpoints are valid URL strings
			throw new RuntimeException("Invalid endpoints in UnuClient", e);
		}
	}
	
	/** Current user's ID or 0 if no user is logged in */
	protected static int userId = 0;
	/** Checks if the user logged in using the cached user ID.
	 * @return True if the user is logged in
	 */
	public static boolean isLoggedInCached() {
		return userId != 0;
	}
	
	/** Checks if the user is currently logged in.
	 * @return True if the user is logged in
	 */
	public static boolean isLoggedIn() {
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
	public static boolean logIn(String username, String password) {
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
	public static void postContent(String body, String source) throws AuthenticationException {
		HashMap<String, String> params = new HashMap<String, String>(2);
		params.put(inboxScrapBody, body);
		params.put(inboxScrapSource, source);
		post(createScrapEndpoint, params);
	}
	
	public static PatchList listPatches() throws AuthenticationException {
		Response response = get(listPatchesEndpoint());
		if (response.getStatusCode() != HttpURLConnection.HTTP_OK) {
			throw new AuthenticationException();
		}
		
		return new PatchList(response.getContent());
	}
	
	public static Inbox getInbox() throws AuthenticationException {
		Response response = get(inboxIdEndpoint);
		if (response.getStatusCode() != HttpURLConnection.HTTP_OK) {
			throw new AuthenticationException();
		}
		
		return new Inbox(response.getContent());
	}
	
	public static Basket getBasket() throws AuthenticationException {
		return new Basket();
	}
	
	public static Friendships getFriendships() throws AuthenticationException {
		Response response = get(listFriendshipsEndpoint());
		if (response.getStatusCode() != HttpURLConnection.HTTP_OK) {
			throw new AuthenticationException();
		}
		
		return new Friendships(response.getContent());
	}
}
