package com.buni.user.vo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2024/5/30 15:01
 */
@Data
@Schema(description = "二维码获取VO")
public class QrCodeVO implements Serializable {

    @Schema(description = "二维码内容")
    @NotBlank(message = "二维码内容不能为空")
    private String code;

    @Schema(description = "二维码logo路径")
    @NotBlank(message = "二维码logo路径不能为空")
    private String logoPath;


}
