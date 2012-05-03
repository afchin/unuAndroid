package unu.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/** Provides methods to access a REST server that uses URL encoded parameters.
 * @author Alexandre Boulgakov
 */
public class RestClient {
	
	/** Represents a REST response with an HTTP status code and content 
	 * @author Alexandre Boulgakov
	 */
	public class Response {
		/** Creates a Response object with the passed response.
		 * @param statusCode The returned HTTP status code
		 * @param content The returned content
		 */
		public Response(int statusCode, Object content) {
			this.statusCode = statusCode;
			this.content = content;
		}
		
		/** HTTP status code */
		private int statusCode;
		/** Gets the HTTP status code associated with this response
		 * @return HTTP status code
		 */
		public int getStatusCode() {
			return statusCode;
		}
		
		/** Returned content */
		private Object content;
		/** Gets the content associated with this response.
		 * The content is decoded using the Content-Type HTTP header of the response
		 * @return The content associated with this response
		 */
		public Object getContent() {
			return content;
		}
	}
	
	/** The name of the charset to use for parameter encoding */
	private static final String charsetName = "UTF-8";
	/** The charset to use for parameter encoding */
	private static final Charset charset = Charset.forName(charsetName);
	
	/** Enables cookies */
	static {
		// Enable VM-wide HTTP cookie management
		CookieHandler.setDefault(new CookieManager());
	}

	/** Issues a parameterless GET request to the specified endpoint
	 * @param endpoint The URL to which the GET request will be sent
	 * @return The response returned by the server
	 */
	public Response get(URL endpoint) {
		HttpURLConnection conn = null;
		try {
			// Connect to endpoint (defaults to GET)
			conn = (HttpURLConnection) endpoint.openConnection();
			// Read and return the result
			return new Response(conn.getResponseCode(), conn.getContent());
		} catch (IOException e) {
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	/** Issues a POST request to the specified endpoint with the specified parameters.
	 * The parameters are sent with the application/x-www-form-urlencoded MIME type.
	 * @param endpoint The URL to which the POST request will be sent
	 * @param params The set of parameter keys and values 
	 * @return The response returned by the server
	 */
	public Response post(URL endpoint, Map<? extends String, ? extends String> params) {
		HttpURLConnection conn = null;
		try {
			// Connect to endpoint
			conn = (HttpURLConnection) endpoint.openConnection();
			// Defaults to POST
			conn.setDoOutput(true);
			// Write the application/x-www-form-urlencoded parameters
			conn.getOutputStream().write(urlEncode(params));
			// Read and return the result
			return new Response(conn.getResponseCode(), conn.getContent());
		} catch (IOException e) {
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/** Separates URL-encoded parameters */
	private static final String paramSeparator = "&";
	/** Separates the key and value of a particular parameter */
	private static final String nameValueSeparator = "=";
	/** Encodes the parameter list as application/x-www-form-urlencoded
	 * @param params The set of parameter keys and values 
	 * @return The encoded bytestring 
	 */
	protected byte[] urlEncode(Map<? extends String, ? extends String> params) {
		StringBuilder encoded = new StringBuilder();
		
		for (Map.Entry<? extends String, ? extends String> param : params.entrySet()) {
			try {
				encoded.append(URLEncoder.encode(param.getKey(), charsetName))
				       .append(nameValueSeparator)
				       .append(URLEncoder.encode(param.getValue(), charsetName))
				       .append(paramSeparator);
			} catch (UnsupportedEncodingException e) {
				// Should not get here since charsetName should be valid
				throw new RuntimeException("Invalid charset name in RestClient: " + charsetName);
			}
		}
		return encoded.toString().getBytes(charset);
	}
}
