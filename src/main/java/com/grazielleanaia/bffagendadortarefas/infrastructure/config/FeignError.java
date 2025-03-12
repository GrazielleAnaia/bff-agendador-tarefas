package com.grazielleanaia.bffagendadortarefas.infrastructure.config;

import com.grazielleanaia.bffagendadortarefas.infrastructure.exception.BusinessException;
import com.grazielleanaia.bffagendadortarefas.infrastructure.exception.ConflictException;
import com.grazielleanaia.bffagendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.grazielleanaia.bffagendadortarefas.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 409:
                return new ConflictException("Erro de atributo ja existente");
            case 403:
                return new ResourceNotFoundException("Atibuto nao encontrado");
            case 401:
                return new UnauthorizedException("Usuario nao autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
