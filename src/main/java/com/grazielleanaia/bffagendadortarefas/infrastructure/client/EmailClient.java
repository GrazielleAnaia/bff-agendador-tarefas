package com.grazielleanaia.bffagendadortarefas.infrastructure.client;


import com.grazielleanaia.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${email.url}")

public interface EmailClient {

    @PostMapping
    void enviaEmail(@RequestBody TarefasDTOResponse dto);

}
