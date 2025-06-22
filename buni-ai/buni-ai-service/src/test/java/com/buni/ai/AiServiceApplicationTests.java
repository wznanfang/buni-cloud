package com.buni.ai;

import com.buni.ai.service.AiChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AI服务测试类
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@SpringBootTest
class AiServiceApplicationTests {

    @Autowired
    private AiChatService aiChatService;

    @Test
    void contextLoads() {
        assertNotNull(aiChatService);
        System.out.println("Spring上下文加载成功");
    }

    @Test
    void testServiceInjection() {
        assertNotNull(aiChatService);
        System.out.println("AI聊天服务注入成功");
    }
}
