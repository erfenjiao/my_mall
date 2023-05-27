package com.atguigu.mxbmall.product;

import com.atguigu.mxbmall.product.entity.BrandEntity;
import com.atguigu.mxbmall.product.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MxbProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    public void contextLoads(){
        //BrandEntity brandEntity = new BrandEntity();
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        list.forEach((item)->{
            System.out.println(item);
        });


//        brandEntity.setBrandId(1L);
//        brandEntity.setDescript("Hawei");
//        brandService.updateById(brandEntity);

        // successful
//        brandEntity.setName("华为");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");


    }
}
