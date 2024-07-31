package ru.puchinets.ratesservice.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.puchinets.ratesservice.model.entity.Currency;
import ru.puchinets.ratesservice.service.RateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates/currencies")
@Tag(name = "CurrencyController", description = "Controller for getting available currencies")
public class CurrencyController {

    private final RateService service;

    @Operation(summary = "Get a list of available currencies", description = "Returns a list of available currencies")
    @ApiResponse(responseCode = "200", description = "The list of currently available currencies", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Currency.class)))
    @GetMapping
    public ResponseEntity<List<Currency>> getCurrencies()  {
        List<Currency> currencies = service.getCurrencies();
        return ResponseEntity.ok(currencies);
    }
}
