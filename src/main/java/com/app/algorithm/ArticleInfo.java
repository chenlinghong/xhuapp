package com.app.algorithm;

/**
 *
 * 文章信息
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 2018/5/19
 * Time: 16:11
 */
public class ArticleInfo {

    private int articleId;      //文章ID
    private int articleType;        //文章类型编号
    private int star;       //文章获得点赞数

    public ArticleInfo() {
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "articleId=" + articleId +
                ", articleType=" + articleType +
                ", star=" + star +
                '}';
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
