package com.magal.transacaoapi.bussiness.services;

import com.magal.transacaoapi.controller.dto.TransacaoRequestDTO;
import com.magal.transacaoapi.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionaTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        log.info("Iniciando transação");

        if(transacaoRequestDTO.dataHora().isAfter(OffsetDateTime.now())){

            log.error("Data hora invalida");

            if(transacaoRequestDTO.dataHora().isBefore(OffsetDateTime.now())){
                throw new UnprocessableEntity("Data e hora maior que a data atual");
            }
            if (transacaoRequestDTO.valor()<0){
                throw new UnprocessableEntity("Valor não pode ser negativo e menor que zero");
            }

            listaTransacoes.add(transacaoRequestDTO);
        }
    }

    public void limpaTransacao() {
        listaTransacoes.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacao(Integer intervaloBusca) {

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        return listaTransacoes.stream()
                .filter( transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntervalo)).collect(Collectors.toList());

    }

}
