package com.example.projetobiblioteca.controller;

import com.example.projetodebiblioteca.Usuario; // Corrigido o caminho do import
import com.example.projetobiblioteca.service.UsuarioService; // Corrigido o caminho do import
import com.example.projetobiblioteca.controller.UsuarioController; // Corrigido o caminho do import
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setEmail("joao.silva@example.com");
    }

    @Test
    void deveRetornarListaDeUsuarios() throws Exception {
        Mockito.when(usuarioService.listarTodos()).thenReturn(Arrays.asList(usuario));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(usuario.getId()))
                .andExpect(jsonPath("$[0].nome").value(usuario.getNome()))
                .andExpect(jsonPath("$[0].email").value(usuario.getEmail()));
    }

    @Test
    void deveRetornarUsuarioPorId() throws Exception {
        Mockito.when(usuarioService.buscarPorId(anyLong())).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuario.getId()))
                .andExpect(jsonPath("$.nome").value(usuario.getNome()))
                .andExpect(jsonPath("$.email").value(usuario.getEmail()));
    }

    @Test
    void deveCriarNovoUsuario() throws Exception {
        Mockito.when(usuarioService.salvar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(usuario.getId()))
                .andExpect(jsonPath("$.nome").value(usuario.getNome()))
                .andExpect(jsonPath("$.email").value(usuario.getEmail()));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        Mockito.when(usuarioService.atualizar(anyLong(), any(Usuario.class))).thenReturn(Optional.of(usuario));

        mockMvc.perform(put("/usuarios/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuario.getId()))
                .andExpect(jsonPath("$.nome").value(usuario.getNome()))
                .andExpect(jsonPath("$.email").value(usuario.getEmail()));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        Mockito.doNothing().when(usuarioService).deletar(anyLong());

        mockMvc.perform(delete("/usuarios/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
