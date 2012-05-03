package unu.rest;

/** Indicates that a REST request did not go through due to bad credentials
 * @author Alexandre Boulgakov
 */
@SuppressWarnings("serial")
public class AuthenticationException extends Exception {
	/** Default constructor */
	public AuthenticationException() {
		super("User is not logged in");
	}
}
