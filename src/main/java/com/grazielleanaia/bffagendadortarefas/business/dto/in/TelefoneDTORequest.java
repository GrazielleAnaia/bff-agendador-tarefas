package com.grazielleanaia.bffagendadortarefas.business.dto.in;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class TelefoneDTORequest {


    private Long id;
    private String numero;
    private String ddd;
}
