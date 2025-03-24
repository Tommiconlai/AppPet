package com.example.apppet.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.ApiService;
import com.example.apppet.R;
import com.example.apppet.RegisterResponse;
import com.example.apppet.RetrofitClient;
import com.example.apppet.utente.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText emailET = findViewById(R.id.usernameEditTxt);
        EditText passwordET = findViewById(R.id.passwordEditTxt);
        sharedPreferences = getSharedPreferences("user_pref",MODE_PRIVATE);


        Button loginBTN = (Button) findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Hai effettuato il login", Toast.LENGTH_SHORT).show();*/
                String email=emailET.getText().toString();
                String password=passwordET.getText().toString();
                checkCredentials(email,password);
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
    private void checkCredentials(String email, String password){
            ApiService apiservice = RetrofitClient.getClient().create(ApiService.class);
            Call<RegisterResponse> call = apiservice.login(new LoginRequest());
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<LoginRequest> call, Response<RegisterResponse> response) {
                        RegisterResponse loginResponse= response.body();
                        if(loginResponse != null && "Login effettuato".equals(loginResponse.getMessage())){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putLong("userId",loginResponse.getUserId());
                            editor.putString("userEmail",loginResponse.getEmail());
                            editor.putString("userPassword", loginResponse.getPassword());

                            editor.apply();

                        }
                    }


                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    RegisterResponse registerResponse= response.body();
                    if(registerResponse != null && "Login effettuato".equals(registerResponse.getMessage())){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putLong("userId",response.body().getUserId());
                        editor.putString("userEmail",registerResponse.getEmail());

                }

            }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {

                });

    }
}

    /* mettere la logica per cui controlla se l'utente è già registrato
    se è già registrato gli fa fare il login e salva i dati nelle shared preferences*/

