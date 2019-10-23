package com.pinyougou.sellergoods.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Brand;

import java.util.List;


public interface BrandService {

    /***
     * 查询所有品牌
     * @return
     */
    List<Brand> getAll();

    /****
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> getAll(int page, int size, Brand brand);

    /***
     * 增加品牌
     * @param brand
     * @return
     */
    int add(Brand brand);

    /***
     * 根据ID查询品牌
     * @param id
     * @return
     */
    Brand getById(Long id);

    int updateById(Brand brand);

    int deleteByIds(List<Long> ids);
}
