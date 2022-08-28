package com.lijiarui.mall.tiny.controller;

import com.lijiarui.mall.tiny.common.api.CommonResult;
import com.lijiarui.mall.tiny.dto.UmsAdminLoginParam;
import com.lijiarui.mall.tiny.mbg.model.UmsAdmin;
import com.lijiarui.mall.tiny.mbg.model.UmsPermission;
import com.lijiarui.mall.tiny.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result){
        CommonResult commonResult = null;
        UmsAdmin umsAdmin=  umsAdminService.register(umsAdminParam);
        if(umsAdmin != null){
            return commonResult.success(umsAdmin);
        }
        return commonResult.failed("注册失败");
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result){
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if(token == null){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("token", token);
        hashMap.put("tokenHead", tokenHeader);
        return CommonResult.success(hashMap);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }

    @ApiOperation("测试controller")
    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public String getTest(){
        return "helloworld";
    }
}
