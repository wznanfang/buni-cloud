package com.buni.ai.service;

import com.buni.ai.vo.spark.TalkVO;

/**
 * @author zp.wei
 * @date 2024/7/31 10:08
 */
public interface SparkService {

    /**
     * 聊天
     * @param talkVO
     * @return
     */
    String talk(TalkVO talkVO);


}
