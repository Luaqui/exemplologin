package com.exemplologin.service;

import com.exemplologin.dto.UsuarioDTO;
import com.exemplologin.entity.Usuario;
import com.exemplologin.mapper.UsuarioMapper;
import com.exemplologin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO salvar(UsuarioDTO dto) {
        Usuario entity = mapper.toEntity(dto);
        entity.setSenha(passwordEncoder.encode(dto.getSenha()));
        Usuario salvo = repository.save(entity);
        return mapper.toDTO(salvo);
    }

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
