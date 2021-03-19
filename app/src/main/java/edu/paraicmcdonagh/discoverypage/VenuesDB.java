package edu.paraicmcdonagh.discoverypage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class VenuesDB extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues_d_b);
        addVenues();
        showVenues();


    }

    public void addVenues()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        db.emptyTable();
        db.addVenue(new MartialArtsClubs("Sligo Martial Arts Academy", "All", "The C.R.I.B Youth Centre", "Rockwood Parade", "Sligo", 54.27184, -8.47463));
        db.addVenue(new MartialArtsClubs("Atlantic Jiu Jistsu Sligo", "Jiu Jitsu", "Unit 9b Cleverage Buisness Park", "Fort Gary Buisiness Centre", "Sligo", 54.26579, -8.45600));
        db.addVenue(new MartialArtsClubs("Carrick-On-Shannon Muay-Thai", "Muay-Thai", "Unit 6", "Lisnabrack", "Carrick On Shannon", 53.95322, -8.09299));
        db.addVenue(new MartialArtsClubs("White Tiger Martial Arts", "All", "McHale Rd", "Business Part", "Castlebar", 53.85482, -9.28520));
        db.addVenue(new MartialArtsClubs("Black Dragon Martial Arts Academy", "All", "Unit 1", "Moynehall", "Cavan", 53.97098, -7.35310));
        db.addVenue(new MartialArtsClubs("Brazillian Jiu Jitsu Cavan", "Jiu Jitsu", "4 Laragh Cres", "Tullymongan Upper", "Cavan", 53.98364, -7.35519));
        db.addVenue(new MartialArtsClubs("Cavan Kendo Kai", "Kendo Kai", "Second Cavan Scouts Den", "Railway Rd", "Cavan", 53.99120, -7.36530));
        db.addVenue(new MartialArtsClubs("Universal Combat Arts Academy", "All", "The Fitness Habit", "Ballybay Road", "Monaghan", 54.23633, -6.96645));
        db.addVenue(new MartialArtsClubs("Martial Arts School of Fitness", "All", "Butterly Business Part", "Marshes Lower", "Dundalk", 54.00015, -6.38446));
    }

    public void showVenues()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        final List<MartialArtsClubs> showallclubs = db.getallVenues();

        ArrayAdapter<MartialArtsClubs> adapter = new ArrayAdapter<MartialArtsClubs>(this,
                android.R.layout.simple_list_item_1, showallclubs);
        setListAdapter(adapter);

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MartialArtsClubs listcontent = showallclubs.get(position);



                Intent i = new Intent(view.getContext(), showClickedItem.class);
                i.putExtra("listContent", listcontent);
                startActivity(i);

            }
        });
    }


}