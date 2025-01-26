package com.magal.transacaoapi.bussiness.services;

import com.magal.transacaoapi.controller.dto.EstatiscasResponseDTO;
import com.magal.transacaoapi.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatiscasService {

    private static final Logger log = LoggerFactory.getLogger(EstatiscasService.class);

    private final TransacaoService transacaoService;

    public EstatiscasResponseDTO calcularEstatiscas(Integer intervaloBusca) {

       log.info("Calculando estatiscas do intervalo {}", intervaloBusca);

       List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(intervaloBusca);

       if(transacoes.isEmpty()) {
           return new EstatiscasResponseDTO(0L,0.0,0.0,0.0,0.0);
       }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        log.info("Estatiscas retornados com sucesso {}", estatisticasTransacoes);

        return new EstatiscasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }


}
