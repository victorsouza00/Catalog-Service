package com.polarbookshop.catalog_service.ServerConfiguration;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer(){
        return factory -> {
            //Server Port
            factory.setPort(9001);

            //Timeouts
            factory.addConnectorCustomizers(connector -> {
                var protocol = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
                // connection-timeout: 2s (em milissegundos)
                protocol.setConnectionTimeout(2000);
                //keep-alive-timeout
                protocol.setKeepAliveTimeout(15000);

            });


            factory.addConnectorCustomizers(connector -> {
                var protocol = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
                //threads.max: 50
                protocol.setMaxThreads(50);
                //threads.mnin-spare: 5
                protocol.setMinSpareThreads(5);
            });


        };
    }


}
