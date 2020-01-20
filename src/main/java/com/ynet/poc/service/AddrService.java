package com.ynet.poc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynet.poc.entity.AddrInfo;
import com.ynet.poc.exception.YNETException;
import com.ynet.poc.mapper.AddrMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 9:11
 * @description：
 * @version: $
 */
@Service
@Slf4j
public class AddrService extends ServiceImpl<AddrMapper, AddrInfo> {

    public void insertAddr(AddrInfo addrInfo) {
        boolean suc = this.save(addrInfo);
        if (!suc) {
            throw new YNETException("9001", "保存地址信息失败!");
        }
    }

    public void updateAddr(AddrInfo addrInfo) {
        boolean suc = this.updateById(addrInfo);
        if (!suc) {
            throw new YNETException("9002", "更新地址信息失败!");
        }
    }

    public List<AddrInfo> getAddrList(int userId) {
        QueryWrapper<AddrInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pai_user_id", userId);
        return this.list(wrapper);
    }

    public void deleteAddr(int addrId) {
        QueryWrapper<AddrInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pai_id", addrId);
        this.remove(wrapper);
    }

}
