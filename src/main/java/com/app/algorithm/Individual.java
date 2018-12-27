package com.app.algorithm;

import java.util.Arrays;

/**
 * 种群个体
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 2018/5/19
 * Time: 16:01
 */
public class Individual {

    private boolean[] gene;     //基因序列
    private double score;       //对应的函数得分

    public Individual() {
    }

    @Override
    public String toString() {
        return "Individual{" +
                "gene=" + Arrays.toString(gene) +
                ", score=" + score +
                '}';
    }

    public boolean[] getGene() {
        return gene;
    }

    public void setGene(boolean[] gene) {
        this.gene = gene;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
