package edu.paraicmcdonagh.discoverypage;

import java.io.Serializable;

public class MartialArtsClubs implements Serializable {

    String venue_id;
    String venue_name;
    String martialarts_type;
    String addone;
    String addtwo;
    String addthree;
    double latitude;
    double longitude;

    public MartialArtsClubs(String venue_id, String venue_name, String martialarts_type, String addone, String addtwo, String addthree, double latitude, double longitude)
    {
        this.venue_id = venue_id;
        this.venue_name = venue_name;
        this.martialarts_type = martialarts_type;
        this.addone = addone;
        this.addtwo = addtwo;
        this.addthree = addthree;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public MartialArtsClubs()
    {

    }

    public MartialArtsClubs(String venue_name, String martialarts_type, String addone, String addtwo, String addthree, double latitude, double longitude)
    {
        this.venue_name = venue_name;
        this.martialarts_type = martialarts_type;
        this.addone = addone;
        this.addtwo = addtwo;
        this.addthree = addthree;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getVenue_id()
    {
        return venue_id;
    }

    public void setVenue_id(String venue_id)
    {
        this.venue_id = venue_id;
    }

    public String getVenue_name()
    {
        return venue_name;
    }

    public void setVenue_name(String venue_name)
    {
        this.venue_name = venue_name;
    }

    public String getMartialarts_type()
    {
        return "Martial Arts Type: " +martialarts_type;
    }

    public void setMartialarts_type(String martialarts_type)
    {
        this.martialarts_type = martialarts_type;
    }

    public String getAddone()
    {
        return "Address Line 1: " +addone;
    }

    public void setAddone(String addone)
    {
        this.addone = addone;
    }

    public String getAddtwo() { return "Address Line 2:" +addtwo; }

    public void setAddtwo(String addtwo)
    {
        this.addtwo = addtwo;
    }

    public String getAddthree()
    {
        return "Address Line 3: " +addthree;
    }

    public void setAddthree(String addthree)
    {
        this.addthree = addthree;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public String toString()
    {
        return venue_name;
    }
}


