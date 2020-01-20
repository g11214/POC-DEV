package com.ynet.poc.controller;

import com.ynet.poc.common.ResponseVo;
import com.ynet.poc.entity.AddrInfo;
import com.ynet.poc.service.AddrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 8:51
 * @description：地址管理
 * @version: $
 */
@RestController
@RequestMapping("/addr")
@Api(value = "地址信息操作接口")
@Slf4j
public class AddrController {
    @Autowired
    AddrService addrService;

    /**
     * @Description: TODO
     * @Param: [addrId]
     * @Return: com.ynet.poc.common.ResponseVo
     * @Date: 2020/1/15
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除地址信息")
    public ResponseVo deleteAddr(@RequestParam("addrId") int addrId, ModelAndView modelAndView) {
        addrService.deleteAddr(addrId);
        return ResponseVo.SUCCESS();
    }

    /**
     * @Description: TODO
     * @Param: [addr 地址信息, mobile 收货手机号, name 收货人]
     * @Return: ResponseVo
     * @Date: 2020/1/15
     **/
    @PostMapping(value = "/add")
    @ApiOperation("新增地址信息")
    public ResponseVo insertAddr(AddrInfo addrInfo, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        log.info(userId + "");
        addrInfo.setAddrUserId(userId);
        addrService.insertAddr(addrInfo);
        return ResponseVo.SUCCESS();
    }

    @PutMapping("/update")
    @ApiOperation("更新地址信息")
    public ResponseVo updateAddr(AddrInfo addrInfo, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        addrInfo.setAddrUserId(userId);
        log.info(addrInfo.toString());
        addrService.updateAddr(addrInfo);
        return ResponseVo.SUCCESS();
    }

    @GetMapping("/getAddrList")
    @ApiOperation("获取地址信息列表")
    public ResponseVo getAddrList(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        List<AddrInfo> addrInfoList = addrService.getAddrList(userId);
        return ResponseVo.SUCCESS().setDataAttribute(addrInfoList);
    }

}
