package ru.puchinets.ratesservice.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.ratesservice.model.entity.Rate;
import ru.puchinets.ratesservice.service.RateService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates")
@Tag(name = "RateController", description = "The controller for receiving exchange rates")
public class RateController {

    private final RateService service;

    @GetMapping
    @Operation(summary = "Get currency exchange rates", description = "Returns a list of currency rates to convert from one to another")
    @ApiResponse(responseCode = "200", description = "The list of exchange rates of the requested currencies has been received", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Rate.class)))
    public ResponseEntity<List<Rate>> getRatesFromToForDate(@Parameter(description = "Date", example = "2024-01-01")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                            @RequestParam(required = false) LocalDate date,
                                                            @Parameter(description = "Which currency to convert from", example = "USD", required = true)
                                                            @RequestParam String from,
                                                            @Parameter(description = "What currency should I convert to", example = "EUR", required = true)
                                                            @RequestParam String to) {

        if (date == null) date = LocalDate.now();
        return ResponseEntity.ok(service.getRates(date, from, to));
    }
    @Operation(summary = "Get the currency exchange rate by code", description = "Returns the exchange rate for a specific or current date")
    @ApiResponse(responseCode = "200", description = "The currency exchange rate has been received", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Rate.class)))
    @ApiResponse(responseCode = "404", description = "The currency exchange rate was not found")
    @GetMapping("/{code}")
    public ResponseEntity<Rate> getRateByCodeForDate(@Parameter(description = "Currency code", example = "USD")
                                                     @PathVariable("code") String code, @Parameter(description = "Date", example = "2024-01-01")
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                     @RequestParam(required = false) LocalDate date) {
        if (date == null) date = LocalDate.now();
        Optional<Rate> rate = service.getRate(date, code);
        return rate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
