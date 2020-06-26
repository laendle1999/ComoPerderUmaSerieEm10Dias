package br.unicamp.ft.d166336_m202618.trashtime.models;

public class SerieTranding {

    private int code;
    private String name, status;

    public SerieTranding(int code, String name, String status) {
        this.code = code;
        this.name = name;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
