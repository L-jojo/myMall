package com.lijiarui.mall.tiny.controller;

import com.lijiarui.mall.tiny.common.api.CommonPage;
import com.lijiarui.mall.tiny.common.api.CommonResult;
import com.lijiarui.mall.tiny.mbg.model.PmsBrand;
import com.lijiarui.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    private static  final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList(){return CommonResult.success(pmsBrandService.listAllBrand());}

    @ApiOperation("添加品牌")
    @RequestMapping(value="/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult = null;
        int count = pmsBrandService.createBrand(pmsBrand);
        if(count == 1){
            commonResult = commonResult.success(pmsBrand);
            LOGGER.debug("createBrand success" , pmsBrand);
        }else{
            commonResult = commonResult.failed("createBrand failed");
            LOGGER.debug("createBrand failed");
        }
        return  commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, PmsBrand pmsBrand){
        CommonResult commonResult = null;
        int count = pmsBrandService.updateBrand(id, pmsBrand);
        if(count == 1){
            commonResult = commonResult.success(pmsBrand);
            LOGGER.debug("update pmsbrand success", pmsBrand);
        }else {
            commonResult = commonResult.failed("update failed");
            LOGGER.debug("update failed", pmsBrand);
        }
        return  commonResult;
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id){
        int count = pmsBrandService.deleteBrand(id);
        CommonResult commonResult = null;
        if(count == 1){
            commonResult = commonResult.success("the" + id + "pmsbrand has been deleted");
            LOGGER.debug("delete success");
        }else{
            commonResult = commonResult.failed("delete failed");
            LOGGER.debug("delete failed");
        }
        return commonResult;
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getBrand(@PathVariable("id") Long id){
        PmsBrand pmsBrand = pmsBrandService.getBrand(id);
        CommonResult commonResult = null;
        if(!(pmsBrand == null)){
            commonResult = commonResult.success("get brand success");
            LOGGER.debug("get success");
        }else{
            commonResult = commonResult.failed("get brand failed");
            LOGGER.debug("get failed");
        }
        return commonResult;
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize){
        List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

}
