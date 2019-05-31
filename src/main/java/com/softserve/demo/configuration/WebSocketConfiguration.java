//package com.softserve.demo.configuration;
//
//
//import com.softserve.demo.interceptor.HttpHandshakeInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//import static com.softserve.demo.configuration.PropertiesConfig.getPropertyValue;
//
//
//@Configuration
//@EnableWebSocketMessageBroker
//@Component
//public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
//
//    private HttpHandshakeInterceptor httpHandshakeInterceptor;
//
//    @Autowired
//    public WebSocketConfiguration(HttpHandshakeInterceptor httpHandshakeInterceptor) {
//        this.httpHandshakeInterceptor = httpHandshakeInterceptor;
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/app");
//        registry.setApplicationDestinationPrefixes("/chat");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//
//        registry.addEndpoint("/socket")
//                .setAllowedOrigins("http://localhost:4200")
//                .withSockJS()
//                .setInterceptors(httpHandshakeInterceptor);
//    }
//}
