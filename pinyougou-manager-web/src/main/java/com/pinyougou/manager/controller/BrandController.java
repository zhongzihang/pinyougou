package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /***
     * 执行删除操作
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody List<Long> ids){
        try {
            //调用Service实现删除
            int dcount = brandService.deleteByIds(ids);

            if(dcount>0){
                return new Result(true);
            }
        } catch (Exception e) {
        }
        return new Result(false, "删除不成功！");
    }

    /****
     * http://localhost:18082/brand/123.shtml
     * http://localhost:18082/brand/{id}.shtml
     * @return
     */
    @RequestMapping(value = "/{id}")
    public Brand getById(@PathVariable(value = "id")Long id){
        //根据ID查询
        return brandService.getById(id);
    }

    /***
     * 修改品牌
     * @return
     */
    @RequestMapping(value = "/modify")
    public Result modify(@RequestBody Brand brand){
        try {
            int mcount = brandService.updateById(brand);
            if(mcount>0){
                return  new Result(true);
            }
        } catch (Exception e) {
        }
        return new Result(false,"修改失败！");
    }


    /***
     * 增加品牌数据
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Brand brand){

        try {
            int acount = brandService.add(brand);
            if(acount>0){
                //增加成功
                return new Result(true);
            }
        } catch (Exception e) {
        }

        //增加失败
        return new Result(false,"增加不成功！");
    }


    /***
     * 增加品牌数据
     */
    @RequestMapping(value = "/add1",method = RequestMethod.POST)
    public Map<String,Object> add1(@RequestBody Brand brand){
        //message:String  消息
        //success:boolean 成功/失败的状态
        Map<String,Object> result = new HashMap<String,Object>();


        try {
            int acount = brandService.add(brand);
            if(acount>0){
                //增加成功
                result.put("success",true);
                result.put("message","增加成功！");
                return result;
            }
        } catch (Exception e) {
        }

        //增加失败
        result.put("success",false);
        result.put("message","增加不成功！");
        return result;
    }

    /****
     * @ResponseBody
     * 响应JSON数据
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageInfo<Brand> list(@RequestParam(value = "page",required = false,defaultValue ="1")int page,
                                @RequestParam(value = "size",required = false,defaultValue ="10")int size,
                                @RequestBody Brand brand){
        //调用Service返回PageInfo
        PageInfo<Brand> pageInfo = brandService.getAll(page,size,brand);
        return  pageInfo;
    }




    /***
     * 返回JSON数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Brand> list(){
        List<Brand> brands = brandService.getAll();
        return brands;
    }
}
