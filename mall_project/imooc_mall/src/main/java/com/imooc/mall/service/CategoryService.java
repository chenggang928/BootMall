package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.vo.CategoryVO;

import java.util.List;

/**
 * 分类目录Service
 */
public interface CategoryService {

    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delect(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
