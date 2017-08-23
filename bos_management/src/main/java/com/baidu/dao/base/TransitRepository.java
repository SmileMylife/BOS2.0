package com.baidu.dao.base;

import com.baidu.domain.TransitInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ZhangPei on 2017/8/20.
 */
public interface TransitRepository extends JpaRepository<TransitInfo, Integer> {
    
}
