package com.example.a1.cinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText emailInput,passwordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailInput=(EditText)findViewById(R.id.email);
        passwordInput=(EditText)findViewById(R.id.password);
        Button button_sign_in=(Button)findViewById(R.id.sign_in);
        Button button_register=(Button)findViewById(R.id.register);

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailInput.getText().toString().trim();
                String password=passwordInput.getText().toString().trim();
                if(!email.isEmpty() && !password.isEmpty())
                {
                    signIn(email,password);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Введите данные",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void signIn(String email,String password)
    {
        final ProgressDialog progressDial=new ProgressDialog(this);
        progressDial.setMessage("Вход...");
        progressDial.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ApiURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service=retrofit.create(ApiService.class);

        Call<Result> call=service.loginUser(email,password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDial.dismiss();
                if(!response.body().getError())
                {
                    finish();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Неверный эмеил или пароль",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDial.dismiss();
                Toast.makeText(getApplicationContext(),"Ошибка соединения",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
