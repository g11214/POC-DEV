package com.ynet.poc.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynet.poc.entity.CartInfo;
import com.ynet.poc.entity.Merchandise;
import com.ynet.poc.mapper.MerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/16 9:33
 * @description：
 * @version: $
 */
@Service
@Slf4j
public class MerchandiseService extends ServiceImpl<MerMapper, Merchandise> {

    public IPage<Merchandise> getMerList(int pages) {
        Page<Merchandise> page = new Page<>(pages, 10);
        IPage<Merchandise> iPage = this.page(page);
        log.info(JSON.toJSONString(iPage));
        return iPage;
    }

    public List<Merchandise> getMerListById(List<CartInfo> cartInfos) {
        List<Integer> merIdList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfos) {
            merIdList.add(cartInfo.getMerId());
        }
        QueryWrapper<Merchandise> wrapper = new QueryWrapper<>();
        wrapper.in("tri_id", merIdList);
        return this.list(wrapper);
    }

    public Merchandise getMerInfoById(int merId) {
        return this.getById(merId);
    }

    public void addMerInfo(Merchandise mer) {
        this.save(mer);
    }

    public void delMerInfoById(int merId) {
        this.removeById(merId);
    }

    public void updateMerInfo(Merchandise mer) {
        this.updateById(mer);
    }
}
