/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_websocket.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.client.WebSocketClient;
import top.yangbuyi.api_websocket.RemoteWebsocketClient;
import top.yangbuyi.service_websocket.server.WebSocketServer;

/**
 * @program: yangbuyi_video
 * @ClassName: WebsocketClient
 * @create: 2021/10/19:16:36
 * @author: Yang Shuai
 * @desc: |
 **/
@RestController
@RequestMapping("/service_websocket/webSocket")
public class WebsocketClient implements RemoteWebsocketClient {

    /**
     * 调用推送消息服务到前端
     */
    @Override
    @GetMapping("sendMessage/{logName}/{msg}")
    public void sendMessage(@PathVariable("logName") String logName, @PathVariable("msg") String msg) {
        WebSocketServer.sendInfo(logName, msg);
    }
}
