package com.buni.ai.controller;

import com.buni.ai.service.SparkService;
import com.buni.ai.vo.spark.TalkVO;
import com.buni.framework.util.Result;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zp.wei
 * @date 2024/7/31 9:53
 */
@Tag(name = "讯飞星火ai")
@ApiSort(1)
@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class SparkController {

    @Resource
    private SparkService sparkService;


    @Operation(summary = "讯飞星火对话")
    @GetMapping("/spark/talk")
    public Result<String> talk(@ParameterObject TalkVO talkVO) {
        return Result.ok(sparkService.talk(talkVO));
    }


}
