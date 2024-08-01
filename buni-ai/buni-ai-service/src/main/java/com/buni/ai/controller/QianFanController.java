package com.buni.ai.controller;

import com.buni.ai.service.QianFanService;
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
 * @date 2024/8/1 9:11
 */
@Tag(name = "千帆大模型")
@ApiSort(1)
@AllArgsConstructor
@RestController
@RequestMapping("v1")
public class QianFanController {

    @Resource
    private QianFanService qianFanService;


    @Operation(summary = "千帆对话")
    @GetMapping("/qianfan/talk")
    public Result<String> talk(@ParameterObject TalkVO talkVO) {
        return Result.ok(qianFanService.talk(talkVO));
    }


}
