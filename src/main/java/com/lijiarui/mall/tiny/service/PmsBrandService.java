package com.lijiarui.mall.tiny.service;

import com.lijiarui.mall.tiny.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int deleteBrand(Long id);

    int updateBrand(Long id, PmsBrand brand);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
