package br.unicamp.ft.d166336_m202618.trashtime.models;

public class SerieList {

    private int id;
    private String name, image;
    private float grade;

    public SerieList(int id, String name, String image, float grade) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
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
