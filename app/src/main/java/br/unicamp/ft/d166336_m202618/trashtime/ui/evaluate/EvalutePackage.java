package br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate;

import java.io.Serializable;

public class EvalutePackage implements Serializable {

    private int id, tmdb_code;
    private float grade;

    public EvalutePackage(int id, int tmdb_code) {
        this.id = id;
        this.tmdb_code = tmdb_code;
        this.grade = 0;
    }

    public EvalutePackage(int id, int tmdb_code, float grade) {
        this.id = id;
        this.tmdb_code = tmdb_code;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public int getTmdbCode() {
        return tmdb_code;
    }

    public float getGrade() {
        return grade;
    }
}
