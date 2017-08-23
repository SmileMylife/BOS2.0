package com.baidu.web.action;

import com.baidu.domain.Customer;
import com.baidu.domain.Message;
import com.baidu.domain.TransitInfo;
import com.baidu.service.base.TransitService;
import com.baidu.web.action.base.BaseAction;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangPei on 2017/8/19.
 */
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class TransitAction extends BaseAction<TransitInfo> {
    @Autowired
    private TransitService ts;

    //创建中转配置
    private String wayBillIds;

    public void setWayBillIds(String wayBillIds) {
        this.wayBillIds = wayBillIds;
    }

    @Action(value = "createTransit", results = {@Result(name = "success", type = "json")})
    public String createTransit() {
        //action层只用来进行数据接收，处理数据在业务层
        Message message = new Message();
        try {
            ts.createTransit(wayBillIds);
            message.setStatus(1);
            message.setMessage("中转配送成功");
        } catch (Exception e) {
            message.setStatus(0);
            message.setMessage(e.getMessage());
            ServletActionContext.getContext().getValueStack().push(message);
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
