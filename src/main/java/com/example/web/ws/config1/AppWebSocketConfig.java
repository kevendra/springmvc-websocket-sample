package com.example.web.ws.config1;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class AppWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes("/app");
		/*
		 * for cluster
		config.enableStompBrokerRelay("/topic/").setRelayHost("relayHost").setRelayPort(65080);
<dependency>
    <groupId>io.projectreactor</groupId>
    <artifactId>reactor-net</artifactId>
    <version>2.0.8.RELEASE</version>
</dependency>
<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.1.6.Final</version>
</dependency>		
		*/
		 
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp").withSockJS();
		registry.addEndpoint("/add");
		// setHandshakeHandler makes Server to accept websocket protocols as it could upgrade the HTTP protocol.
		// registry.addEndpoint("/add").setHandshakeHandler(new DefaultHandshakeHandler(new JettyRequestUpgradeStrategy()));
		// registry.addEndpoint("/add").setAllowedOrigins("*").withSockJS();
	}
	
}
