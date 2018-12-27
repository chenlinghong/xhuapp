package com.app.util;

import com.app.algorithm.ArticleInfo;
import com.app.algorithm.GeneticAlgorithm;
import com.app.service.IDynamicService;
import com.app.service.IInterestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 2018/5/20
 * Time: 10:54
 */
public class GAUtil {

    @Autowired
    private IDynamicService dynamicService;

    @Autowired
    private IInterestService interestService;

    public List<Integer> getByGA(int user_id) throws Exception{

        List<ArticleInfo> articleInfoList = dynamicService.findIdLookPersonsInterestIdByUser_id(user_id);
        List<Map<Integer, Integer>> mapList = interestService.getMap(user_id);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        List<Integer> result = geneticAlgorithm.listByGeneticAlgorithm(articleInfoList, mapList);

        return result;
    }


}
