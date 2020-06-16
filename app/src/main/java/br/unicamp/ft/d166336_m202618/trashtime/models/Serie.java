package br.unicamp.ft.d166336_m202618.trashtime.models;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;

import br.unicamp.ft.d166336_m202618.trashtime.R;

public class Serie {

    private int foto;
    private String name;
    private float grade;

    public Serie(int foto, String name, float grade) {
        this.foto = foto;
        this.name = name;
        this.grade = grade;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public static Serie[] getAlunos (Context context) {
        if (context == null) {
            return null;
        }

        String[] infos = context.getResources().getStringArray(R.array.series);
        TypedArray fotos = context.getResources().obtainTypedArray(R.array.fotos);

        Serie[] series = new Serie[infos.length];

        for (int i = 0; i < series.length; i++){
            String[] info = infos[i].split(",");

            series[i] = new Serie(
                    fotos.getResourceId(i, 0),
                    info[0],
                    Float.parseFloat(info[1]));
        }

        return series;
    }

    public static Serie[] search (Context context, String search) {
        if (context == null) {
            return null;
        }

        String[] infos = context.getResources().getStringArray(R.array.series);
        TypedArray fotos = context.getResources().obtainTypedArray(R.array.fotos);

        ArrayList<Serie> series = new ArrayList<>();

        for (int i = 0; i < infos.length; i++){
            String[] info = infos[i].split(",");

            if (info[0].contains(search)){
                series.add (
                        new Serie(
                            fotos.getResourceId(i, 0),
                            info[0],
                            Float.parseFloat(info[1]))
                );

            }
        }

        return series.toArray(new Serie[series.size()]);
    }
}
