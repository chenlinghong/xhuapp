package com.app.algorithm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 2018/5/19
 * Time: 21:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class GeneticAlgorithmTest {

    @Test
    public void testListByGeneticAlgorithm() throws Exception{

        List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
        List<Map<Integer, Integer>> userStar = new ArrayList<Map<Integer, Integer>>();

        for (int i=0;i<100;i++){
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setArticleId(i);
            articleInfo.setArticleType(i);
            articleInfo.setStar((int)(Math.random() * 100));
            articleInfoList.add(articleInfo);

            userStar.add(new HashMap<Integer, Integer>(i,((int)(Math.random() * 1000))));
        }

        System.out.println("-------------------------------------------");
        System.out.println(userStar);
        System.out.println(articleInfoList);
        System.out.println("-------------------------------------------");

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        List<Integer> result = geneticAlgorithm.listByGeneticAlgorithm(articleInfoList, userStar);

        System.out.println("-----------------------------------");
        System.out.println(result);
        System.out.println("-----------------------------------");
    }

}
