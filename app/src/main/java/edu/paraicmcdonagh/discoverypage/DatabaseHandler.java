package edu.paraicmcdonagh.discoverypage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "venuesDB";
    private static final String TABLE_VENUES = "venues";
    private static final String KEY_VENUEID = "venue_id";
    private static final String KEY_VENUE_NAME = "venue_name";
    private static final String KEY_MARTIALARTS_TYPE = "martialarts_type";
    private static final String KEY_ADDONE = "addone";
    private static final String KEY_ADDTWO = "addtwo";
    private static final String KEY_ADDTHREE = "addthree";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_VENUES_TABLE = "CREATE TABLE " + TABLE_VENUES + "(" +
                KEY_VENUEID + " INTEGER PRIMARY KEY," +
                KEY_VENUE_NAME + " TEXT NOT NULL," +
                KEY_MARTIALARTS_TYPE + " TEXT NOT NULL," +
                KEY_ADDONE + " TEXT NOT NULL," +
                KEY_ADDTWO + " TEXT NOT NULL," +
                KEY_ADDTHREE + " TEXT NOT NULL," +
                KEY_LATITUDE + " REAL," +
                KEY_LONGITUDE + " REAL" +
                ")";
        db.execSQL(CREATE_VENUES_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENUES);

        onCreate(db);
    }

    public void emptyTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENUES);

        onCreate(db);
    }

    void addVenue(MartialArtsClubs martialartsclub)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VENUE_NAME, martialartsclub.getVenue_name());
        values.put(KEY_MARTIALARTS_TYPE, martialartsclub.getMartialarts_type());
        values.put(KEY_ADDONE, martialartsclub.getAddone());
        values.put(KEY_ADDTWO, martialartsclub.getAddtwo());
        values.put(KEY_ADDTHREE, martialartsclub.getAddthree());
        values.put(KEY_LATITUDE, martialartsclub.getLatitude());
        values.put(KEY_LONGITUDE, martialartsclub.getLongitude());

        db.insert(TABLE_VENUES, null, values);

        db.close();
    }

    MartialArtsClubs getVenue(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_VENUES, new String[]{
                        KEY_VENUEID,
                        KEY_VENUE_NAME,
                        KEY_MARTIALARTS_TYPE,
                        KEY_ADDONE,
                        KEY_ADDTWO,
                        KEY_ADDTHREE,
                        KEY_LONGITUDE,
                        KEY_LATITUDE
                },
                KEY_VENUE_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        MartialArtsClubs venue = new MartialArtsClubs(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getDouble(6),
                cursor.getDouble(7));


        return venue;

    }

    public List<MartialArtsClubs> getallVenues(){
        List<MartialArtsClubs> venueList = new ArrayList<MartialArtsClubs>();

        String selectQuery = "SELECT * FROM " + TABLE_VENUES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MartialArtsClubs venue = new MartialArtsClubs();
                venue.setVenue_id(cursor.getString(0));
                venue.setVenue_name(cursor.getString(1));
                venue.setMartialarts_type(cursor.getString(2));
                venue.setAddone(cursor.getString(3));
                venue.setAddtwo(cursor.getString(4));
                venue.setAddthree(cursor.getString(5));
                venue.setLatitude(cursor.getDouble(6));
                venue.setLongitude(cursor.getDouble(7));

                venueList.add(venue);
            }while (cursor.moveToNext());
        }

        return venueList;
    }

    public int updateVenues(MartialArtsClubs venue) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VENUE_NAME, venue.getVenue_name());
        values.put(KEY_MARTIALARTS_TYPE, venue.getMartialarts_type());
        values.put(KEY_ADDONE, venue.getAddone());
        values.put(KEY_ADDTWO, venue.getAddtwo());
        values.put(KEY_ADDTHREE, venue.getAddthree());
        values.put(KEY_LATITUDE, venue.getLatitude());
        values.put(KEY_LONGITUDE, venue.getLongitude());

        return db.update(TABLE_VENUES, values, KEY_VENUEID + " = ?",
                new String[]{String.valueOf(venue.getVenue_id())});

    }

    public void deletevenue(MartialArtsClubs venue){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VENUES, KEY_VENUEID + " = ?",
                new String[]{String.valueOf(venue.getVenue_id())});
        db.close();
    }



}
