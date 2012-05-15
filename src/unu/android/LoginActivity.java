package unu.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import unu.rest.UnuClient;

public class LoginActivity extends Activity{
  EditText username, password;
  TextView status;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      // setting default screen to login.xml
      setContentView(R.layout.login);

      Button btnLogin = (Button) findViewById(R.id.btnLogin);
      
      username = (EditText) findViewById(R.id.username);
      password = (EditText) findViewById(R.id.password);
      status = (TextView) findViewById(R.id.status);
      status.setText("");
      btnLogin.setOnClickListener(new View.OnClickListener() {
    	  @Override
          public void onClick(View v) {
              String uname = username.getText().toString();
              String pword = password.getText().toString();
              
              if (UnuClient.logIn(uname, pword)){
                Intent i = new Intent(getApplicationContext(), UnuActivity.class);
                startActivity(i);
                
              } else {
                status.setText("Incorrect password");
              }

          }
      });
  }
}
