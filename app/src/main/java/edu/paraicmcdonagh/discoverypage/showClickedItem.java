package edu.paraicmcdonagh.discoverypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class showClickedItem extends AppCompatActivity {

    String content;
    TextView VenueName, MartialArtType, AddOne, AddTwo, AddThree, Latitude, Longitude;
    String venueName, martialArtsType, addone, addtwo, addthree;
    Double latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clicked_item);

        TextView showlistcontent;



        Serializable listcontents = getIntent().getSerializableExtra("listContent");
        content = listcontents.toString();



        VenueName = findViewById(R.id.venueName);
        MartialArtType = findViewById(R.id.martialartsType);
        AddOne = findViewById(R.id.addOne);
        AddTwo = findViewById(R.id.addTwo);
        AddThree = findViewById(R.id.addThree);


        getInfo();


    }

    public void getInfo()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        MartialArtsClubs info = db.getVenue(content);
         venueName = info.getVenue_name();
         martialArtsType = info.getMartialarts_type();
         addone = info.getAddone();
         addtwo = info.getAddtwo();
         addthree = info.getAddthree();
        latitude = info.getLatitude();
        longitude = info.getLongitude();

        VenueName.setText(venueName);
        MartialArtType.setText(martialArtsType);
        AddOne.setText(addone);
        AddTwo.setText(addtwo);
        AddThree.setText(addthree);

    }

    public void showonMap(View view)
    {
        String showselecteditemonMap = "1";
        Intent passcoordinates = new Intent(view.getContext(), MainActivity.class);
        passcoordinates.putExtra("latitude", latitude );
        passcoordinates.putExtra("longitude", longitude);
        passcoordinates.putExtra("showselecteditemonMap", showselecteditemonMap);

        startActivity(passcoordinates);
    }
}