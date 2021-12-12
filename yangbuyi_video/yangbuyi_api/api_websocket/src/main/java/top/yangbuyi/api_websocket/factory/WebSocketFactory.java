/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_websocket.factory;


import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import top.yangbuyi.api_websocket.RemoteWebsocketClient;

/**
 * @program: yangbuyi_video
 * @ClassName: WebSocketFactory
 * @create: 2021/10/20:10:00
 * @author: Yang Shuai
 * @desc: |
 **/
@Component
public class WebSocketFactory implements FallbackFactory<RemoteWebsocketClient> {
    private static final Logger logger = LogManager.getLogger(WebSocketFactory.class);

    @Override
    public RemoteWebsocketClient create (Throwable cause) {
        logger.error("websocket服务模块错误:{}", cause.getLocalizedMessage());
        return new RemoteWebsocketClient() {
            @Override
            public void sendMessage (String logName, String msg) {

            }
        };
    }
}
