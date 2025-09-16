package com.example.diffkis1ssys.controller;

import com.example.diffkis1ssys.service.TtService;
import java.io.Serializable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/diff-kis1s-sys/1c")
public class TtController {

    private final TtService ttService;

    public TtController(TtService ttService) {
        this.ttService = ttService;
    }

    @GetMapping("/get-tt/{code}")
    public Mono<Map<String, Serializable>> getTt(@PathVariable String code) {
        return ttService.getTtInfo(code);
    }
}
