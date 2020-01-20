package com.ynet.poc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.poc.common.ResponseVo;
import com.ynet.poc.entity.Merchandise;
import com.ynet.poc.service.MerchandiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/16 9:32
 * @description：商品信息
 * @version: $
 */
@RestController
@RequestMapping("/mer")
@Slf4j
@Api(value = "商品操作")
public class MerchandiseController {
    @Autowired
    MerchandiseService merchandiseService;

    @GetMapping("/getMerList")
    @ApiOperation("获取商品列表")
    public ResponseVo getMerList(int page) {
        IPage<Merchandise> iPage = merchandiseService.getMerList(page);
        return ResponseVo.SUCCESS().setDataAttribute(iPage);
    }

    @GetMapping("/getMerInfo")
    @ApiOperation("获取单个商品")
    public ResponseVo getMerInfoById(int merId) {
        Merchandise merchandise = merchandiseService.getMerInfoById(merId);
        return ResponseVo.SUCCESS().setDataAttribute(merchandise);
    }

    @PutMapping("/updateMerInfo")
    @ApiOperation("修改商品信息")
    public ResponseVo updateMerInfo(Merchandise mer) {
        log.info(mer.toString());
        merchandiseService.updateMerInfo(mer);
        return ResponseVo.SUCCESS();
    }

    @DeleteMapping("/delMerInfo")
    @ApiOperation("删除单个商品")
    public ResponseVo delMerInfo(int merId) {
        merchandiseService.delMerInfoById(merId);
        return ResponseVo.SUCCESS();
    }

    @PostMapping("/addMerInfo")
    @ApiOperation("增加单个商品")
    public ResponseVo addMerInfo(Merchandise mer) {
        merchandiseService.addMerInfo(mer);
        return ResponseVo.SUCCESS();
    }
}
