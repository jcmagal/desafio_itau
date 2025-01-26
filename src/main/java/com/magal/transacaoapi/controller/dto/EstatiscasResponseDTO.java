package com.magal.transacaoapi.controller.dto;

public record EstatiscasResponseDTO(Long count,
                                    Double sum,
                                    Double avg,
                                    Double min,
                                    Double max) {
}
