package com.ynet.poc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynet.poc.entity.CartInfo;
import com.ynet.poc.mapper.CartMapper;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/16 11:36
 * @description：
 * @version: $
 */
@Service
@Slf4j
@Transactional
public class CartService extends ServiceImpl<CartMapper, CartInfo> {
    @Autowired
    MerchandiseService merchandiseService;

    /**
     * 添加商品到购物车
     *
     * @param cartInfo
     */
    public void addCartInfo(CartInfo cartInfo) {
        //判断商品是否已在购物车中
        int count = getCartCount(cartInfo.getUserId(), cartInfo.getMerId());
        log.info("库存数量为："+count);
        if (count != 0) {//在购物车中，增加商品的数量
            updateMerCount(cartInfo.getUserId(), cartInfo.getMerId(), count + cartInfo.getMerCount());
        } else {//不在，则新增
            this.save(cartInfo);
        }
    }

    @Synchronized
    public void updateMerCount(int userId, int merId, int merCount) {
        UpdateWrapper<CartInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("tci_user_id", userId).eq("tci_mer_id", merId).set("tci_count", merCount);
        this.update(wrapper);
    }

    public int getCartCount(int userId, int merId) {
        int count=0;
        QueryWrapper<CartInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("tci_user_id", userId).eq("tci_mer_id", merId);
        try{
            count = this.getOne(wrapper).getMerCount();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return count;
    }

    public void updateMerCount(int cartId, int merCount) {
        UpdateWrapper<CartInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("tci_id", cartId).set("tci_count", merCount);
        this.update(wrapper);
    }

    public void delCartInfo(int id) {
        this.removeById(id);
    }

    public void delCartList(List<Integer> ids) {
        this.removeByIds(ids);
    }

    @Synchronized
    public void updateCartInfo(CartInfo cartInfo) {
        this.updateById(cartInfo);
    }

    public List<CartInfo> getCartList(int userId) {
        List<CartInfo> cartInfos = this.baseMapper.cartList(userId);
        return cartInfos;
    }
}
