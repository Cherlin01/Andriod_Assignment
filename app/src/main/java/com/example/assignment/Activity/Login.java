package com.example.assignment.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.DataManager.LoginDataManager;
import com.example.assignment.R;
import com.example.assignment.Session.Session;

public class Login extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button BtnLogin;
    LoginDataManager LDM;

    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(Login.this);

        txtUsername = findViewById(R.id.txtName);
        txtPassword = findViewById(R.id.txtPass);
        BtnLogin = findViewById(R.id.btnLogin);

        LDM = new LoginDataManager();

        if(session.getLogin())
        {
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Status = LDM.GetLoginStatus(txtUsername.getText().toString(), txtPassword.getText().toString());
                if(Status == null){
                    Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
                else if (Status.equals("Pass"))
                {
                    Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    session.setLogin(true);
                    session.setUsername(txtUsername.getText().toString());
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                }
            }
        });
    }
}