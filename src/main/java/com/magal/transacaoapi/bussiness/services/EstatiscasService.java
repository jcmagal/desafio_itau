package com.magal.transacaoapi.bussiness.services;

import com.magal.transacaoapi.controller.dto.EstatiscasResponseDTO;
import com.magal.transacaoapi.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatiscasService {

    public final TransacaoService transacaoService;

    public EstatiscasResponseDTO calcularEstatiscas(Integer intervaloBusca) {
       List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(intervaloBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        return new EstatiscasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }


}
