package com.example.diffkis1ssys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() throws SSLException {
        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(
                        SslContextBuilder.forClient()
                                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                ));

        return WebClient.builder()
                .clientConnector(new org.springframework.http.client.reactive.ReactorClientHttpConnector(httpClient));
    }
}
