package com.example.diffkis1ssys.service;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class TtService {

    private final WebClient webClient;

    @Value("${external.api.internal-key}")
    private String internalKey;

    public TtService(WebClient.Builder builder) {
       // this.webClient = builder.baseUrl("https://localhost:8291/1s").build();
       // https://localhost:8243/1s-mock/1.0.0/pos/111
       this.webClient = builder.baseUrl("https://localhost:8243/1s-mock/1.0.0").build();
    }

    public Mono<Map<String, Serializable>> getTtInfo(String code) {
        System.out.println("code "+code);
        return webClient.get()
                .uri("/pos/{code}", code)
                .header("Internal-Key", internalKey)
                .retrieve()
                .bodyToMono(Map.class)
                .map(source -> Map.ofEntries(
                        Map.entry("type", "ITEM"),
                        Map.entry("identifier", 44673662),
                        Map.entry("primaryKey", "254563"),
                                                Map.entry("displayName",
                                source.get("naimenovanie") + " " +
                                        source.get("naimenovanie_regiona") + ", " +
                                        source.get("raion_okrug") + ", " +
                                        source.get("gorod") + " г"),
                        Map.entry("companyName", "tander"),
                        Map.entry("containerName", "Подразделения"),
                        Map.entry("dataCompletenessPercent", new Object[]{}),
                        Map.entry("validationErrorCount", 0),
                        Map.entry("lastModifiedOn", ""),
                        Map.entry("lastModifiedBy", "esb"),
                        Map.entry("versionNumber", 25),
                        Map.entry("DivnameAndAddress",
                                source.get("naimenovanie") + " " +
                                        source.get("naimenovanie_regiona") + ", " +
                                        source.get("raion_okrug") + ", " +
                                        source.get("gorod") + " г"),
                        Map.entry("NetworkName", ""),
                        Map.entry("EmailToTS", "false"),
                        Map.entry("Address",
                                source.get("naimenovanie_regiona") + ", " +
                                        source.get("raion_okrug") + ", " +
                                        source.get("gorod") + " г"),
                        Map.entry("AjacentArea", ""),
                        Map.entry("Diskaunter", "false")
                ))
                .onErrorResume(WebClientResponseException.class, e -> Mono.just(Map.of(
                        "error", "Ошибка при вызове 1s-mock",
                        "status", e.getStatusCode().value(),
                        "message", e.getResponseBodyAsString()
                )))
                .onErrorResume(Exception.class, e -> Mono.just(Map.of(
                        "error", "Внутренняя ошибка diff-kis1s-sys",
                        "status", "",
                        "message", e.getMessage()
                )));
    }
}
