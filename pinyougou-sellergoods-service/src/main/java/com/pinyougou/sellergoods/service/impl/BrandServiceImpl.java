package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.model.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;


    public List<Brand> getAll() {
        return brandMapper.getAllBrand();
    }


    public PageInfo<Brand> getAll(int page, int size,Brand brand) {
        //分页
        PageHelper.startPage(page,size);

        //模糊查询
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //select * from tb_brand where name like '%红%' and firstChar='A'

        if(brand!=null){
            if(StringUtils.isNotBlank(brand.getName())){
                //name模糊查询
                criteria.andLike("name","%"+brand.getName()+"%");
            }

            if(StringUtils.isNotBlank(brand.getFirstChar())){
                //firstChar条件判断
                criteria.andEqualTo("firstChar",brand.getFirstChar());
            }
        }

        //集合查询
        List<Brand> brands = brandMapper.selectByExample(example);

        //集合查询
        //List<Brand> brands = brandMapper.selectAll();

        //封装PageInfo
        return new PageInfo<Brand>(brands);
    }


    public int add(Brand brand) {
        int acount = brandMapper.insertSelective(brand);
        System.out.println(brand);
        return acount;
    }


    public Brand getById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }


    public int updateById(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective(brand);
    }


    public int deleteByIds(List<Long> ids) {
        //批量删除 delete from tb_brand where id IN(1,343,545,45)
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andIn("id",ids);
        return brandMapper.deleteByExample(example);
    }


}
