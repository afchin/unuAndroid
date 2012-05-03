package unu.rest;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class RestClient {
	
	public class Response {
		public Response(int statusCode, Object content) {
			this.statusCode = statusCode;
			this.content = content;
		}
		
		private int statusCode;
		public int getStatusCode() {
			return statusCode;
		}
		private Object content;
		public Object getContent() {
			return content;
		}
	}
	
	private static final Charset charset = Charset.forName("UTF-8");
	
	static {
		// Enable VM-wide HTTP cookie management
		CookieHandler.setDefault(new CookieManager());
	}

	public Response get(URL endpoint) {
		throw new UnsupportedOperationException("GET support not implemented in RestClient yet");
	}
	
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

	public Response delete(URL endpoint) {
		throw new UnsupportedOperationException("DELETE support not implemented in RestClient yet");
	}
	
	public Response put(URL endpoint, Map<? extends String, ? extends String> params) {
		throw new UnsupportedOperationException("PUT support not implemented in RestClient yet");
	}
	
	private static final String paramSeparator = "&";
	private static final String nameValueSeparator = "=";
	protected byte[] urlEncode(Map<? extends String, ? extends String> params) {
		StringBuilder encoded = new StringBuilder();
		
		for (Map.Entry<? extends String, ? extends String> param : params.entrySet()) {
			encoded.append(param.getKey())
			       .append(nameValueSeparator)
			       .append(param.getValue())
			       .append(paramSeparator);
		}
		return encoded.toString().getBytes(charset);
	}
}
