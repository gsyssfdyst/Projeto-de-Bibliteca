package com.example.projetobiblioteca.repository;

import com.example.projetobiblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário.
}
