package com.magal.transacaoapi.controller;

import com.magal.transacaoapi.bussiness.services.EstatiscasService;
import com.magal.transacaoapi.controller.dto.EstatiscasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisca")
@RequiredArgsConstructor
public class EstatiscasController {

    private final EstatiscasService estatiscasService;

    @GetMapping
    @Operation(description = "Endpoint para buscar estatiscas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Erro de requisiçã")
    })
    public ResponseEntity<EstatiscasResponseDTO> buscarEstatiscas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatiscasService.calcularEstatiscas(intervaloBusca));

    }



}
