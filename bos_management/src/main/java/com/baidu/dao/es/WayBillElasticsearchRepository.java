package com.baidu.dao.es;

import com.baidu.domain.WayBill;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Smile_Mylife on 2017/8/13.
 */
public interface WayBillElasticsearchRepository extends ElasticsearchRepository<WayBill,Integer> {

}
