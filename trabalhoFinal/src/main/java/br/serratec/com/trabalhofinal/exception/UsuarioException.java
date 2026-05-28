package br.serratec.com.trabalhofinal.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioException extends UsernameNotFoundException {

    public UsuarioException(String message) {
        super(message);
    }

}