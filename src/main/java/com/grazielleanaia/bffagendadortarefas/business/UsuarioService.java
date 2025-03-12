package com.grazielleanaia.bffagendadortarefas.business;


import com.grazielleanaia.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.grazielleanaia.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import com.grazielleanaia.bffagendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioService(UsuarioClient client) {
        this.client = client;
    }

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTORequest loginDTORequest) {
        return client.login(loginDTORequest);
    }


    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        client.deleteUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco,
                                                EnderecoDTORequest enderecoDTO, String token) {
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);

    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token) {
        return client.atualizaTelefone(dto, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
        return client.cadastraEndereco(dto, token);

    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
        return client.cadastraTelefone(dto, token);
    }

    public ViaCepDTOResponse buscarEnderecoPorCep(String cep) {
        return client.buscarDadosCep(cep);
    }

}
