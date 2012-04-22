package unu.rest;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class RestTest {
  public static void main(String[] args){
    // register team info
    System.out.println("Loggin in to unu");
    HttpClient client = new DefaultHttpClient();
    HttpPost post = new HttpPost("http://unu.fm/users/login");

    try {
      // create the data to be sent
      List<NameValuePair> dataPairs = new ArrayList<NameValuePair>();
      dataPairs.add(new BasicNameValuePair("user[email]","spazznie@gmail.com"));
      dataPairs.add(new BasicNameValuePair("user[password]", "andalite"));

      post.setEntity(new UrlEncodedFormEntity(dataPairs));
      HttpResponse response = client.execute(post);

      String sc = response.getStatusLine().toString();
      System.out.println("Response Code for team registration:" + sc);
      
      InputStream is = response.getEntity().getContent();
      InputStreamReader inR = new InputStreamReader(is);
      
      BufferedReader buf = new BufferedReader( inR );
      String line;
      while ( ( line = buf.readLine() ) != null ) {
        System.out.println( line );
      }

    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}