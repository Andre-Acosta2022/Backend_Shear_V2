package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Acceso;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, Long> {

}
