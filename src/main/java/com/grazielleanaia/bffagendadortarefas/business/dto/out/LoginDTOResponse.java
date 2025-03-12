package com.grazielleanaia.bffagendadortarefas.business.dto.out;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginDTOResponse {
    private String email;

    private String senha;
}
