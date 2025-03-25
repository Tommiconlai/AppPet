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
    LoginRequest utente = new LoginRequest("", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText emailET = findViewById(R.id.usernameEditTxt);
        EditText passwordET = findViewById(R.id.passwordEditTxt);
        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);


        Button loginBTN = findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                checkCredentials(email, password);

            }
        });

        TextView goToRegistration = findViewById(R.id.registrazioneTxt);
        goToRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrazioneActivity.class);
            startActivity(intent);
        });
    }

    private void checkCredentials(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        ApiService apiservice = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiservice.login(loginRequest);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {

                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && "Login effettuato".equals(registerResponse.getMessage())) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putLong("userId", registerResponse.getUserId());
                        editor.putString("userEmail", registerResponse.getEmail());
                        editor.putString("userPassword", registerResponse.getPassword());
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login non corretto", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (response.code() == 401) {
                        Toast.makeText(LoginActivity.this, "Credenziali non valide", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Errore durante il login", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

    /* mettere la logica per cui controlla se l'utente è già registrato
    se è già registrato gli fa fare il login e salva i dati nelle shared preferences*/

