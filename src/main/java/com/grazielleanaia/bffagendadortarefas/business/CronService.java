package com.grazielleanaia.bffagendadortarefas.business;


import com.grazielleanaia.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.time}")

    public void buscaTarefasProximaHora() {

        String token = login(converteParaDTORequest());

        //log.info("Iniciando busca por tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefasDTOResponse> tarefasLista = tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        tarefasLista.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
           // log.info("Email enviado ao usuario" + tarefa.getEmailUsuario());
            tarefaService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });

        //log.info("Notificacao de tarefas foi realizado com sucesso");

    }

    public String login(UsuarioDTORequest usuarioDTORequest) {
        return usuarioService.loginUsuario(usuarioDTORequest);
    }

//    public LoginDTORequest converteParaRequestDTO() {
//        return LoginDTORequest.builder()
//                .email(email)
//                .senha(senha)
//                .build();
//    }

    public UsuarioDTORequest converteParaDTORequest() {
        return UsuarioDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
