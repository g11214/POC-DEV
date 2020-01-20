package com.ynet.poc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynet.poc.entity.MerStateInfo;
import com.ynet.poc.mapper.MerStateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/16 17:13
 * @description：
 * @version: $
 */
@Service
@Slf4j
public class MerStateService extends ServiceImpl<MerStateMapper, MerStateInfo> {
    /**
     * @Description: 插入订单商品状态
     * @Param: [merStateInfos]
     * @Return: void
     * @Date: 2020/1/17
     **/
    public void addMerStateList(ArrayList<MerStateInfo> merStateInfos) {
        this.saveBatch(merStateInfos);
    }

    public void addMerStateInfo(MerStateInfo merStateInfo) {
        this.save(merStateInfo);
    }
}
