package com.baidu.service.impl;

//import com.baidu.dao.es.WayBillElasticsearchRepository;
import com.baidu.dao.base.WayBillRepository;
import com.baidu.domain.WayBill;
import com.baidu.service.base.WayBillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhangPei on 2017/8/10.
 */
@Service
@Transactional
public class WayBillServiceImpl implements WayBillService {
    @Autowired
    private WayBillRepository wbr;

//    @Autowired
//    private WayBillElasticsearchRepository wber;
    //保存运单
    @Override
    public void saveWayBill(WayBill model) {
        //拿到运单之后并不是直接进行保存操作
        WayBill wayBill = wbr.findByWayBillNum(model.getWayBillNum());
        if (wayBill == null) {
            //之前不存在
            wbr.save(model);
//            wber.save(model);
        }else {
            //之前就已经存在
            if (wayBill.getSignStatus() == 1) {
                Integer id = wayBill.getId();
                BeanUtils.copyProperties(wayBill,model);
                wayBill.setId(id);
                wayBill.setSignStatus(1);
            } else {
                throw new RuntimeException("运单已经发出，无法进行修改操作！");
            }
//            wber.save(wayBill);
        }
    }

    //分页查询
    @Override
    public Page<WayBill> wayBillPageQuery(Pageable pageable) {
        Page<WayBill> page = wbr.findAll(pageable);
        return page;
    }

    //查询运单
    @Override
    public WayBill findWayBillByWayBillNum(String wayBillNum) {
        WayBill wayBill = wbr.findByWayBillNum(wayBillNum);
        return wayBill;
    }
}
