package br.unicamp.ft.d166336_m202618.trashtime.models;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.unicamp.ft.d166336_m202618.trashtime.R;

public class Serie {

    private int id, tmdb_code;
    private String name, image;
    private float grade;
    private Date date;
    private final String TABLE = "series";

    public Serie(int tmdb_code, String name, String image, float grade) {
        this.id = 0;
        this.tmdb_code = tmdb_code;
        this.name = name;
        this.image = image;
        this.grade = grade;
    }

    public Serie(int id, int tmdb_code, String name, String image, float grade) {
        this.id = id;
        this.tmdb_code = tmdb_code;
        this.name = name;
        this.image = image;
        this.grade = grade;
    }

    public Serie() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTmdb_code() {
        return tmdb_code;
    }

    public void setTmdb_code(int tmdb_code) {
        this.tmdb_code = tmdb_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }


    public String getFormattedImage() {
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate(String format) {

        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            return dateFormat.format(date);
        }

        return "n/a";
    }

    public void setDate(String date, String format) {
        try {

            DateFormat dateFormat = new SimpleDateFormat(format);
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
