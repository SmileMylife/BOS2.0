package com.baidu.service.impl;

import com.baidu.dao.base.TransitRepository;
import com.baidu.dao.base.WayBillRepository;
import com.baidu.domain.TransitInfo;
import com.baidu.domain.WayBill;
import com.baidu.service.base.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhangPei on 2017/8/19.
 */
@Service
@Transactional
public class TransitServiceImpl implements TransitService {
    @Autowired
    private WayBillRepository wbr;

    @Autowired
    private TransitRepository tr;

    //中转配送
    @Override
    public void createTransit(String wayBillIds) {
        String[] ids = wayBillIds.split("-");
        for (String id : ids) {
            if (id.equals("1")) {
                //说明订单在未发货状态，产生中转信息
                WayBill wayBill = wbr.findOne(Integer.parseInt(id));
                TransitInfo transitInfo = new TransitInfo();
                transitInfo.setWayBill(wayBill);
                transitInfo.setStatus("出入库中转");
                tr.save(transitInfo);
                wayBill.setSignStatus(2);
            }else {
                throw new RuntimeException("运单已经发出，不能再进行中转配送！");
            }
        }
    }
}
