package com.app.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 遗传算法
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 2018/5/19
 * Time: 15:43
 */
public class GeneticAlgorithm {


    /**
     * 遗传算法获得个性化推荐文章列表
     *
     * @param articleInfoList 文章信息列表
     *                        articleId ： 文章ID
     *                        articleType ： 文章类型编号
     *                        star ： 文章获得点赞数
     * @param userStar        用户对各种数据类型的投票率
     *                        key ： 类型编号
     *                        value ： 投票率
     * @return 个性化推荐信息列表（文章ID）
     * @throws Exception
     */
    public List<Integer> listByGeneticAlgorithm(List<ArticleInfo> articleInfoList, List<Map<Integer, Integer> > userStar)
            throws Exception {
        //获得种群初始化参数
        int numOfIndividuals = articleInfoList.size();      //个体总数
        int curOfIndividuals = 10;
        int numOfMutation = 1;      //基因变异概率
        double allAdaptability = 0;     //适应度总和
        int generation = 0;     //第几代
        double times = 1.5;     //倍率

        double adaptability = 0;        //当前种群适应度

        if (curOfIndividuals > articleInfoList.size()){
            curOfIndividuals = articleInfoList.size();
        }

        /*初始化种群*/
        //一、生成基因序列
        List<Boolean[]> genes = new ArrayList<Boolean[]>();
        for (int i = 0; i < curOfIndividuals; i++) {
            genes.add(getGene(i));
        }

        //计算种群适应度
        adaptability = getAdaptability(genes, articleInfoList, userStar);

        //预估总体适应度峰值
        allAdaptability = adaptability * (numOfIndividuals / curOfIndividuals);

        while (true) {
            generation++;
            //计算种群适应度
            adaptability = getAdaptability(genes, articleInfoList, userStar);
            if ((adaptability >= (allAdaptability * (curOfIndividuals / numOfIndividuals) * times))) {
                //停止条件
                break;
            }
            //交配繁殖下一代
            List<Boolean[]> newGenes = new ArrayList<Boolean[]>();
            for (int i = 0; i < curOfIndividuals / 3; i++) {
                int a = ((int) (Math.random() * curOfIndividuals)) % curOfIndividuals;
                int b = ((int) (Math.random() * curOfIndividuals)) % curOfIndividuals;
                List<Boolean[]> children = genetic(genes.get(a), genes.get(b));
                newGenes.add(children.get(0));
                if (((int) (Math.random() * curOfIndividuals) % curOfIndividuals)
                        > (curOfIndividuals - curOfIndividuals / 10)) {
                    newGenes.add(mutation(numOfMutation, children.get(1)));
                } else {
                    newGenes.add(children.get(1));
                }
            }
            genes = newGenes;
            if (curOfIndividuals > genes.size()){
                curOfIndividuals = genes.size();
            }
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < curOfIndividuals; i++) {
            if (getGeneId(genes.get(i)) < numOfIndividuals) {
                result.add(articleInfoList.get(getGeneId(genes.get(i))).getArticleId());
            }
        }
        return result;
    }

    /**
     * 遗传产生下一代
     *
     * @param a
     * @param b
     * @return
     * @throws Exception
     */
    public List<Boolean[]> genetic(Boolean[] a, Boolean[] b) throws Exception {
        List<Boolean[]> result = new ArrayList<Boolean[]>();
        int len = a.length;
        Boolean[] temp = new Boolean[a.length];
        for (int i = 0; i < len; i++) {
            temp[i] = a[i];
        }
        for (int i = 0; i < len / 2; i++) {
            a[i] = b[i];
        }
        for (int i = len / 2; i < len; i++) {
            b[i] = temp[i];
        }
        result.add(a);
        result.add(b);
        return result;
    }


    /**
     * 计算适应度（此算法设计并非是计算个体适应度，而是种群的适应度）
     *
     * @param genes
     * @param articleInfoList
     * @param userStar
     * @return
     * @throws Exception
     */
    public double getAdaptability(List<Boolean[]> genes, List<ArticleInfo> articleInfoList,
                                  List<Map<Integer, Integer> > userStar) throws Exception {
        double result = 0;
        int genesLen = genes.size();
        for (int i = 0; i < genesLen; i++) {
            Boolean[] gene = genes.get(i);
            int num = getGeneId(gene);
            if (i < userStar.size()){
                if (num < articleInfoList.size()){
                    if (articleInfoList.get(num) != null){
                        result += userStar.get(i).get(articleInfoList.get(num).getArticleType());
                    }
                }
            }
        }
        return result;
    }

    /**
     * 基因变异操作
     *
     * @param num
     * @param gene
     * @return
     * @throws Exception
     */
    public Boolean[] mutation(int num, Boolean[] gene) throws Exception {
        int size = gene.length;
        for (int i = 0; i < num; i++) {
            //寻找变异位置
            int at = ((int) (Math.random() * size)) % size;
            //变异
            gene[at] = !gene[at];
        }
        return gene;
    }


    /**
     * 通过基因ID获得基因串
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Boolean[] getGene(int id) throws Exception {
        int geneLen = 10;   //基因长度
        Boolean[] gene = new Boolean[geneLen];      //基因序列
        for (int i = geneLen - 1; i >= 0; i--) {
            if (id == 0) {
                gene[i] = false;
            } else {
                if (id % 2 == 1) {
                    gene[i] = true;
                } else {
                    gene[i] = false;
                }
            }
            id /= 2;
        }
        return gene;
    }

    /**
     * 通过基因串获得基因ID
     *
     * @param gene
     * @return
     * @throws Exception
     */
    public int getGeneId(Boolean[] gene) throws Exception {
        int result = 0;
        int base = 1;
        for (int i = gene.length - 1; i >= 0; i--) {
            if (gene[i]) {
                result += base;
            }
            base *= 2;
        }
        return result;
    }


}
