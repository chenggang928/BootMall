package com.imooc.mall.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.CategoryMapper;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.vo.CategoryVO;
import com.imooc.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录分类Service实现类
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq) {
        Category category=new Category();
        BeanUtils.copyProperties(addCategoryReq,category);
        Category categoryOld=categoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null){
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        int count=categoryMapper.insertSelective(category);
        if (count == 0){
            throw new ImoocMallException(ImoocMallExceptionEnum.CREATE_FAILED);
        }
    }

    @Override
    public void update(Category updateCategory){
        if (updateCategory.getName() != null) {
            Category categoryOld=categoryMapper.selectByName(updateCategory.getName());
            if (categoryOld != null && !categoryOld.getId().equals(updateCategory.getId())){
                throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
            }
        }
        int count=categoryMapper.updateByPrimaryKeySelective(updateCategory);
        if (count==0){
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delect(Integer id){
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
        if (categoryOld==null){
            throw new ImoocMallException(ImoocMallExceptionEnum.DELECT_FAILED);
        }
        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count==0){
            throw new ImoocMallException(ImoocMallExceptionEnum.DELECT_FAILED);
        }
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,"type,order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo pageInfo = new PageInfo(categoryList);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "listCategoryForCustomer")
    public List<CategoryVO> listCategoryForCustomer(Integer parentId){
        ArrayList<CategoryVO> categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList,parentId);
        return categoryVOList;
    }

    private void recursivelyFindCategories( List<CategoryVO> categoryVOList,Integer parentId){
        //递归获取所有子类别，并组合成为一个“目录树”
        List<Category> categoryList = categoryMapper.selectCategoriesByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)){
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVO categoryVO = new CategoryVO();
                BeanUtils.copyProperties(category,categoryVO);
                categoryVOList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChileCateGory(),categoryVO.getId());
            }
        }
    }
}
