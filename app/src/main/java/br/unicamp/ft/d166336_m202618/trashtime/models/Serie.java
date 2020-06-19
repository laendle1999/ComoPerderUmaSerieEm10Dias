package br.unicamp.ft.d166336_m202618.trashtime.models;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;

import java.util.ArrayList;

import br.unicamp.ft.d166336_m202618.trashtime.R;

public class Serie {

    private int id, tmdb_code;
    private String name, image;
    private float grade;
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
}
