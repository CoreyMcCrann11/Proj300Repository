package edu.paraicmcdonagh.discoverypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class profilePage extends AppCompatActivity {
    TextView name, preferredmartialart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        String Name, martialart;

        name = findViewById(R.id.tvName);
        preferredmartialart = findViewById(R.id.tvpreferredmartialart);

        Name = getIntent().getStringExtra("UserName");

        name.setText(Name);
    }
}