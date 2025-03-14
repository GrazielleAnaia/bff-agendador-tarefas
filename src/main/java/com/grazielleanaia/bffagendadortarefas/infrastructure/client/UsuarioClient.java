package com.grazielleanaia.bffagendadortarefas.infrastructure.client;


import com.grazielleanaia.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")

public interface UsuarioClient {


    @GetMapping("/usuario")
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDTORequest usuarioDTORequest);


    @DeleteMapping("/{email}")
    void deleteUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);


    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable ("cep") String cep);


}
