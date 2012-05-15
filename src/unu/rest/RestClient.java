package unu.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/** Provides methods to access a REST server that uses URL encoded parameters.
 * @author Alexandre Boulgakov
 */
public abstract class RestClient {
	
	/** Represents a REST response with an HTTP status code and content 
	 * @author Alexandre Boulgakov
	 */
	public static class Response {
		
		/** Creates a Response object with the passed response.
		 * @param statusCode The returned HTTP status code
		 * @param content The returned content
		 */
		public Response(int statusCode, String content) {
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
		private String content;
		/** Gets the content associated with this response.
		 * @return The response content
		 */
		public String getContent() {
			return content;
		}
	}
	
	/** The name of the charset to use for parameter encoding */
	private static final String charsetName = "UTF-8";
	
	/** Keep track of cookie at the class level to avoid the need for serialization */
	private static String cookie = null;

	/** Issues a parameterless GET request to the specified endpoint
	 * @param endpoint The URL to which the GET request will be sent
	 * @return The response returned by the server
	 */
	public static Response get(URL endpoint) {
		HttpURLConnection conn = null;
		try {
			/* *** REQUEST *** */
			
			// Connect to endpoint (defaults to GET)
			conn = (HttpURLConnection) endpoint.openConnection();
			// Intercept redirects
			conn.setInstanceFollowRedirects(false);
			// Send cookie
			if (cookie != null) {
				conn.setRequestProperty("Cookie", cookie);
			}
			
			
			/* *** RESPONSE *** */
			
			// Receive cookie
			if (conn.getHeaderField("Set-Cookie") != null) {
				cookie = conn.getHeaderField("Set-Cookie");
			}
			// Read and return the result
			return new Response(conn.getResponseCode(), readStream(conn.getInputStream()));
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
	public static Response post(URL endpoint, Map<? extends String, ? extends String> params) {
		HttpURLConnection conn = null;
		try {
			/* *** REQUEST *** */
			
			// Connect to endpoint
			conn = (HttpURLConnection) endpoint.openConnection();
			// Intercept redirects
			conn.setInstanceFollowRedirects(false);
			// Send cookie
			if (cookie != null) {
				conn.setRequestProperty("Cookie", cookie);
			}
			// Defaults to POST
			conn.setDoOutput(true);

			// Write the application/x-www-form-urlencoded parameters
			conn.getOutputStream().write(urlEncode(params));
			
			
			/* *** RESPONSE *** */
			
			// Receive cookie
			if (conn.getHeaderField("Set-Cookie") != null) {
				cookie = conn.getHeaderField("Set-Cookie");
			}
			// Read and return the result
			return new Response(conn.getResponseCode(), readStream(conn.getInputStream()));
		} catch (IOException e) {
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	/** Reads contents of {@code is} to end
	 * @param is The {@link InputStream} to read
	 * @return The contents of {@code is}
	 * @throws IOException if there is an error reading {@code is}.
	 */
	public static String readStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder result = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			result.append(line);
			line = br.readLine();
		}
		br.close();
		return result.toString();
	}

	/** Separates URL-encoded parameters */
	private static final String paramSeparator = "&";
	/** Separates the key and value of a particular parameter */
	private static final String nameValueSeparator = "=";
	/** Encodes the parameter list as application/x-www-form-urlencoded
	 * @param params The set of parameter keys and values 
	 * @return The encoded bytestring 
	 */
	protected static byte[] urlEncode(Map<? extends String, ? extends String> params) {
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
		try {
			return encoded.toString().getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			// Should not get here since charsetName should be valid
			throw new RuntimeException("Invalid charset name in RestClient: " + charsetName);
		}
	}
}
