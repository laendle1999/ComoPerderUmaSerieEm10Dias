package br.unicamp.ft.d166336_m202618.trashtime.ui.evaluate;

import java.io.Serializable;

public class EvalutePackage implements Serializable {

    private int id, tmdb_code;

    public EvalutePackage(int id, int tmdb_code) {
        this.id = id;
        this.tmdb_code = tmdb_code;
    }

    public int getId() {
        return id;
    }

    public int getTmdbCode() {
        return tmdb_code;
    }
}
