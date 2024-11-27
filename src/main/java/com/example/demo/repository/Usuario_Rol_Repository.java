package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.usuario_rol;



public interface Usuario_Rol_Repository  extends JpaRepository<usuario_rol,Long>{
	List<usuario_rol> findByRolId(Long rolId);
    Optional<usuario_rol> findByUsuario_Persona_IdAndRol_Id(@Param("personaId") Long personaId, @Param("rolId") Long rolId);
}
