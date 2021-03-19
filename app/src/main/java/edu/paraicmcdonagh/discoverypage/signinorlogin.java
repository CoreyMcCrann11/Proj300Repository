package edu.paraicmcdonagh.discoverypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class signinorlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinorlogin);
    }

    public void login(View view)
    {
        Intent login = new Intent(view.getContext(), login.class);
        startActivity(login);
    }

    public void signUp(View view)
    {
        Intent signup = new Intent(view.getContext(), SignIn.class);
        startActivity(signup);
    }
}