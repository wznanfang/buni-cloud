package com.buni.ai.service;

import com.buni.ai.vo.qianfan.TalkVO;

/**
 * @author zp.wei
 * @date 2024/7/31 10:08
 */
public interface QianFanService {

    /**
     * 聊天
     * @param talkVO
     * @return
     */
    String talk(TalkVO talkVO);


}
