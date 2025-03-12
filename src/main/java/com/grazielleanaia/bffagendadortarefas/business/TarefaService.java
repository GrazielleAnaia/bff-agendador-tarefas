package com.grazielleanaia.bffagendadortarefas.business;


import com.grazielleanaia.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.grazielleanaia.bffagendadortarefas.infrastructure.client.TarefasClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class TarefaService {
    private final TarefasClient tarefasClient;

    public TarefaService(TarefasClient tarefasClient) {
        this.tarefasClient = tarefasClient;
    }

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return tarefasClient.updateTarefas(dto, id, token);
    }
}
