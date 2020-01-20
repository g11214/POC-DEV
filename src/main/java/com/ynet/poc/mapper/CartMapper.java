package com.ynet.poc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ynet.poc.entity.CartInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface CartMapper extends BaseMapper<CartInfo> {
    //    @Select("SELECT tci_id, tci_mer_id, tci_count, tci_create_time, tri_expense, tri_name, tri_desc, tri_cover FROM trade_cart_info LEFT JOIN trade_mer_info on tri_id= tci_mer_id WHERE tci_user_id = #{userId}")
    List<CartInfo> cartList(@Param("userId") int userId);
}
