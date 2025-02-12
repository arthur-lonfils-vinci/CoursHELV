package be.vinci.cae.controllers;

import be.vinci.cae.models.Quote;
import be.vinci.cae.models.Resto;
import be.vinci.cae.services.QuotesService;
import be.vinci.cae.services.RestosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restos")
public class RestoController {
    private final RestosService restosService;

    public RestoController(RestosService restosService) {
        this.restosService = restosService;
    }

    @GetMapping("/")
    public List<Resto> resto(@RequestParam(required = false) String cuisine) {
        if (cuisine == null || cuisine.isEmpty()) {
            return restosService.getAllRestos();
        } else {
            return restosService.getAllRestos().stream()
                    .filter(resto -> resto.getType().equals(cuisine))
                    .collect(Collectors.toList());
        }
    }
}
