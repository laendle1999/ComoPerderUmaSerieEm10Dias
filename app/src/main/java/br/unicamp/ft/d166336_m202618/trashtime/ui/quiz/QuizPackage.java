package br.unicamp.ft.d166336_m202618.trashtime.ui.quiz;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class QuizPackage implements Serializable {

    private int result;
    private String name, sleep, thanos, cooker, worstFinal, signo;
    private List<String> food;

    public QuizPackage() {
        Random random = new Random();
        this.result = random.nextInt(3) + 1;
    }

    public QuizPackage(String name, String sleep, String thanos, String cooker, String worstFinal, String signo, List<String> food) {
        this.name = name;
        this.sleep = sleep;
        this.thanos = thanos;
        this.cooker = cooker;
        this.worstFinal = worstFinal;
        this.signo = signo;
        this.food = food;

        Random random = new Random();
        this.result = random.nextInt(3) + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getThanos() {
        return thanos;
    }

    public void setThanos(String thanos) {
        this.thanos = thanos;
    }

    public String getCooker() {
        return cooker;
    }

    public void setCooker(String cooker) {
        this.cooker = cooker;
    }

    public String getWorstFinal() {
        return worstFinal;
    }

    public void setWorstFinal(String worstFinal) {
        this.worstFinal = worstFinal;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public List<String> getFood() {
        return food;
    }

    public void setFood(List<String> food) {
        this.food = food;
    }

    public int getResult() {
        return result;
    }
}
