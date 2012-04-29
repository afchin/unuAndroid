package unu.rest;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RestClient {
	
	public class Response<T> {
		public Response(int statusCode, T content) {
			this.statusCode = statusCode;
			this.content = content;
		}
		
		private int statusCode;
		public int getStatusCode() {
			return statusCode;
		}
		private T content;
		public T getContent() {
			return content;
		}
	}
	
	static {
		// Enable VM-wide HTTP cookie management
		CookieHandler.setDefault(new CookieManager());
	}
	
	public Object submitRequest(URL endpoint) {
		return submitRequest(endpoint, "GET", null);
	}
	
	public Object submitRequest(URL endpoint, String method) {
		return submitRequest(endpoint, method, null);
	}
	
	public Response<Object> submitRequest(URL endpoint, String method,
			Map<? extends String, ? extends String> params) {
		// Connect to endpoint
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) endpoint.openConnection();
		} catch (IOException e) {
			return null;
		}
		
		// Set up URL connection for various methods
		if (method.equalsIgnoreCase("GET")) {
			throw new UnsupportedOperationException("GET support not implemented in RestClient yet");
		} else if (method.equalsIgnoreCase("POST")) {
			throw new UnsupportedOperationException("POST support not implemented in RestClient yet");
		} else if (method.equalsIgnoreCase("PUT")) {
			throw new UnsupportedOperationException("PUT support not implemented in RestClient yet");
		} else if (method.equalsIgnoreCase("DELETE")) {
			throw new UnsupportedOperationException("DELETE support not implemented in RestClient yet");
		} else {
			throw new UnsupportedOperationException(method + " support not implemented in RestClient yet");
		}
		
		// Uncomment the following lines when any HTTP method is implemented:
		/*try {
			return new Response<Object>(conn.getResponseCode(), conn.getContent());
		} catch (IOException e) {
			return null;
		}*/
	}
}
