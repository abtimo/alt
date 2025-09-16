package com.example.mockexp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/1s")
public class PosController {
    

    @GetMapping("/pos/{code}")
    public ResponseEntity<Map<String, Object>> getPosInfo(@PathVariable String code) {
        System.out.println("getPosInfo begin");
//        Map<String, Object> response = Map.of(
//                "kod_magazina", "800662",
//                "naimenovanie", "Борино",
//                "format", "ММ",
//                "naimenovanie_regiona", "Владимирская Область",
//                "raion_okrug", "Александровский",
//                "gorod", "Александров",
//                "adres", "Ленина 8",
//                "typ_otkrytia", "Аренда",
//                "etajnost", "1 этаж",
//                "filial", "Владимир",
//                "okrug", "Московский округ",
//                "subformat", "Дискаунтер"
//        );


//String JSON_SOURCE = "{\"kod_magazina\":\"800662\",\"naimenovanie\":\"Борино\",\"format\":\"ММ\",\"naimenovanie_regiona\":\"Владимирская Область\",\"raion_okrug\":\"Александровский\",\"gorod\":\"Александров\",\"adres\":\"Ленина 8\",\"typ_otkrytia\":\"Аренда\",\"etajnost\":\"1 этаж\",\"filial\":\"Владимир\",\"okrug\":\"Московский округ\",\"subformat\":\"Дискаунтер\"}";
  
String JSON_SOURCE = "{\"kod_magazina\":\""+ code +"\",\"naimenovanie\":\"Борино\",\"format\":\"ММ\",\"naimenovanie_regiona\":\"Владимирская Область\",\"raion_okrug\":\"Александровский\",\"gorod\":\"Александров\",\"adres\":\"Ленина "+ code +"\",\"typ_otkrytia\":\"Аренда\",\"etajnost\":\""+ code +" этаж\",\"filial\":\"Владимир\",\"okrug\":\"Московский округ\",\"subformat\":\"Дискаунтер\"}";

System.out.println("getPosInfo JSON_SOURCE prepared " + JSON_SOURCE);

   Map<String,Object> response;
        try {
           response = new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);
           System.out.println("getPosInfo response prepared");
        } catch (JsonProcessingException ex) {
            response = Map.of("","");
            System.getLogger(PosController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            System.out.println("getPosInfo empty prepared");
        }
          
        System.out.println("getPosInfo before ResponseEntity return");
          
        return ResponseEntity.ok(response);
    }
}
