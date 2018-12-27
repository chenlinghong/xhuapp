package com.app.algorithm;

import java.util.List;

/**
 * 种群
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 2018/5/19
 * Time: 16:04
 */
public class Population {

    private List<Individual> individualList;        //种群个体
    private double score;       //此种群的适应度
    private int generation;     //第几代

    @Override
    public String toString() {
        return "Population{" +
                "individualList=" + individualList +
                ", score=" + score +
                ", generation=" + generation +
                '}';
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public List<Individual> getIndividualList() {
        return individualList;
    }

    public void setIndividualList(List<Individual> individualList) {
        this.individualList = individualList;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Population() {

    }
}
