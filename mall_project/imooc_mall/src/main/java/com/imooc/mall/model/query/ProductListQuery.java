package com.imooc.mall.model.query;

import java.util.List;

/**
 * 查询商品列表的Query
 */
public class ProductListQuery {

    private String Keyword;

    private List<Integer> categoryIds;

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
