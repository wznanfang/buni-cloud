package com.buni.ai;

import com.buni.ai.service.SparkService;
import com.buni.ai.vo.spark.TalkVO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class AiServiceApplicationTests {

    @Resource
    private SparkService sparkService;

    @Test
    void contextLoads() {
        TalkVO talkVO = new TalkVO();
        talkVO.setQuestion("1+1等于几？");
        talkVO.setUid("1");
        sparkService.talk(talkVO);
    }

}
