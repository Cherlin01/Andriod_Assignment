package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.Activity.Home;
import com.example.assignment.DataManager.LoginDataManager;
import com.example.assignment.Session.Session;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button BtnLogin;
    LoginDataManager LDM;

    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(MainActivity.this);

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
                    Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
                else if (Status.equals("Pass"))
                {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    session.setLogin(true);
                    session.setUsername(txtUsername.getText().toString());
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        });
    }
}