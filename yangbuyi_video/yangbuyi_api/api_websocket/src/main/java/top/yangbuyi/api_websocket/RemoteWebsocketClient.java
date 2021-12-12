/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_websocket;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.yangbuyi.api_websocket.factory.WebSocketFactory;
import top.yangbuyi.common_utils.constant.CloudConstant;

/**
 * @program: yangbuyi_video
 * @ClassName: RemoteWebsocketClient
 * @create: 2021/10/20:9:59
 * @author: Yang Shuai
 * @desc: api接口|
 **/
@FeignClient(value = CloudConstant.WEBSOCKET, fallbackFactory = WebSocketFactory.class)
public interface RemoteWebsocketClient {
    @GetMapping("/service_websocket/webSocket/sendMessage/{logName}/{msg}")
    public void sendMessage(@PathVariable("logName") String logName, @PathVariable("msg") String msg);
}
