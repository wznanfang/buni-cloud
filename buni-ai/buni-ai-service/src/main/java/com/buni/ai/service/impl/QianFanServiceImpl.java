package com.buni.ai.service.impl;

import com.buni.ai.manager.QianFanManager;
import com.buni.ai.manager.SparkManager;
import com.buni.ai.service.QianFanService;
import com.buni.ai.service.SparkService;
import com.buni.ai.vo.spark.TalkVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zp.wei
 * @date 2024/7/31 10:08
 */
@Slf4j
@Service
public class QianFanServiceImpl implements QianFanService {

    @Resource
    private QianFanManager qianFanManager;


    /**
     * 讯飞星火
     *
     * @param talkVO
     * @return
     */
    @Override
    public String talk(TalkVO talkVO) {
        return qianFanManager.talk(talkVO);
    }


}
