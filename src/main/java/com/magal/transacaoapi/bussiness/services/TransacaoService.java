package com.magal.transacaoapi.bussiness.services;

import com.magal.transacaoapi.controller.dto.TransacaoRequestDTO;
import com.magal.transacaoapi.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);


    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionaTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        log.info("Iniciando o processo de transação");

        if (transacaoRequestDTO.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora inválida: maior que a data atual");
            throw new UnprocessableEntity("Data e hora maior que a data atual");
        }

        if (transacaoRequestDTO.valor() < 0) {
            log.error("Valor inválido: menor que zero");
            throw new UnprocessableEntity("Valor não pode ser negativo e menor que zero");
        }

        listaTransacoes.add(transacaoRequestDTO);
        log.info("Transação adicionada com sucesso: {}", transacaoRequestDTO);
    }


    public void limpaTransacao() {
        log.info("Deletando transacao");
        listaTransacoes.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacao(Integer intervaloBusca) {
        log.info("Buscando transacao por intervalo {}", intervaloBusca);

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        return listaTransacoes.stream()
                .filter( transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntervalo)).collect(Collectors.toList());

    }

}
