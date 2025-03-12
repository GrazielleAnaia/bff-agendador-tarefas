package com.grazielleanaia.bffagendadortarefas.controller;


import com.grazielleanaia.bffagendadortarefas.business.TarefaService;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.grazielleanaia.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")

//@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)


public class TarefasController {

    private final TarefaService tarefaService;

    public TarefasController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    @Operation(summary = "salvar tarefas de usuarios", description = "Cria uma nova tarefa.")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefas cadastradas por periodo.")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email de usuario", description = "Busca tarefas por email de usuarios.")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id", description = "Deleta tarefas cadastradas por Id.")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status dados de tarefas", description = "Altera status dados de tarefas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Status de tarefas alteradas.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados de tarefas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.updateTarefas(dto, id, token));
    }

}
