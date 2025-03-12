package com.grazielleanaia.bffagendadortarefas.business.dto.in;


import com.grazielleanaia.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioDTORequest {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTOResponse>telefones;

}
