package com.grazielleanaia.bffagendadortarefas.controller;


import com.grazielleanaia.bffagendadortarefas.business.UsuarioService;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import com.grazielleanaia.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
//@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro de login de usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "salvar usuarios", description = "Cria um novo usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso.")
    @ApiResponse(responseCode = "400", description = "Usuario ja cadastrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "login usuarios", description = "Login de usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public String login(@RequestBody UsuarioDTORequest usuarioDTORequest
    ) {
     return usuarioService.loginUsuario(usuarioDTORequest);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuarios por email", description = "Buscar dados de usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado.")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuarios por Id", description = "Deleta usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuarios", description = "Atualizar dados de usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuario nao cadastrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereco de usuarios", description = "Atualiza endereco de usuario.")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuarios", description = "Atualiza telefone de usuario.")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereco de usuarios", description = "Salva endereco de usuario.")
    @ApiResponse(responseCode = "200", description = "Endereco salvo com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<EnderecoDTOResponse>cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                               @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuarios", description = "Salva telefone de usuario.")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereco pelo cep", description = "Busca dados de endereco recebendo um cep.")
    @ApiResponse(responseCode = "200", description = "Dados de endereco retornados com sucesso.")
    @ApiResponse(responseCode = "400", description = "Cep invalido nao encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<ViaCepDTOResponse> buscaDadosEndereco(@PathVariable ("cep") String cep) {
        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));
    }
}
