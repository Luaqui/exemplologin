package com.exemplologin.controller;

import com.exemplologin.dto.UsuarioDTO;
import com.exemplologin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO dto) {
        UsuarioDTO salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioAutenticado(Authentication authentication) {
        UsuarioResponseDTO usuario = UsuarioService.buscarUsuarioAutenticado(authentication.getName());
        return ResponseEntity.ok(usuario);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listartodos() {
        List<UsuarioDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}