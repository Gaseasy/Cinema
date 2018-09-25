package com.example.a1.cinema;

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

public class RegisterActivity extends AppCompatActivity {
    private EditText nameInput,emailInput,passwordInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameInput=(EditText)findViewById(R.id.register_name);
        emailInput=(EditText)findViewById(R.id.register_email);
        passwordInput=(EditText)findViewById(R.id.register_password);
        registerButton=(Button)findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=nameInput.getText().toString().trim();
                final String email=emailInput.getText().toString().trim();
                String password=passwordInput.getText().toString().trim();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(ApiURL.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiService service=retrofit.create(ApiService.class);
                Call<Result> call=service.createUser(name,email,password);
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        .addUser(name,email,null,null);
                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Ошибка",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
