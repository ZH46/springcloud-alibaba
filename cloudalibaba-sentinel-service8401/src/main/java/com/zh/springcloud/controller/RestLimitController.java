package com.zh.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import com.zh.springcloud.handler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 卓少武
 * @date 2020/10/19
 */
@RestController
@Slf4j
public class RestLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, IdUtil.simpleUUID()));
    }

    public CommonResult handleException(BlockException blockException){
        return new CommonResult<>(444, blockException.getClass().getCanonicalName()+"\t服务不可用" );
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200, "by url限流测试OK", new Payment(2020L, IdUtil.simpleUUID()));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "客户自定义 限流测试OK", new Payment(2020L, IdUtil.simpleUUID()));
    }

}
