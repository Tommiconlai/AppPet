package com.example.apppet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBTN = (Button) findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Hai effettuato il login", Toast.LENGTH_SHORT).show();
            }
        });

        TextView goToRegistration = (TextView) findViewById(R.id.registrazioneTxt);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrazioneActivity.class);
                startActivity(intent);
            }
        });
    }
}