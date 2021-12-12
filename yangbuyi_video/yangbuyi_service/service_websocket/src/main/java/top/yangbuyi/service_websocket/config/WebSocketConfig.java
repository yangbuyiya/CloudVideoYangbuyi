/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * 配置文件
 *
 * @author Yang Buyi
 * @date 2021/10/25
 */
@Configuration
public class WebSocketConfig {


	/**
	 * 给Spring容器注入 ServerEndpointExporter 对象
	 *
	 * @return {@link ServerEndpointExporter}
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}