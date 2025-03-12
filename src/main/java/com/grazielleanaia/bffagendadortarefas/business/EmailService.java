package com.grazielleanaia.bffagendadortarefas.business;

import com.grazielleanaia.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.grazielleanaia.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;


    public void enviaEmail(TarefasDTOResponse tarefasDTO) {
        emailClient.enviaEmail(tarefasDTO);
    }

}
