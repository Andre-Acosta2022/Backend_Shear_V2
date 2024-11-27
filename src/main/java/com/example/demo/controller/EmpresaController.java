package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.demo.dto.RegistroDTO;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.RepresentanteLegal;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.RepresentanteLegalRepository;
import com.example.demo.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private RepresentanteLegalRepository representanteLegalRepository;

    // Leer todas las empresas
    @GetMapping
    public ResponseEntity<List<Empresa>> readAll() {
        List<Empresa> lista = service.readAll();
        return lista.isEmpty() 
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Crear nueva empresa
    @PostMapping
    public ResponseEntity<Empresa> create(@Valid @RequestBody Empresa obj) {
        Empresa empresaCreada = service.create(obj);
        return new ResponseEntity<>(empresaCreada, HttpStatus.CREATED);
    }

    // Leer empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> read(@PathVariable Long id) {
        Optional<Empresa> empresa = service.read(id);
        return empresa.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar empresa
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @Valid @RequestBody Empresa obj) {
        Optional<Empresa> empresaExistente = service.read(id);
        if (empresaExistente.isPresent()) {
            obj.setIdEmpresa(id); // Asegurar que el ID se mantiene al actualizar
            Empresa empresaActualizada = service.update(obj);
            return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Registrar empresa con su representante legal y estado "En Proceso"
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarEmpresa(@RequestBody RegistroDTO dto) {
        try {
            // Crear RepresentanteLegal
            RepresentanteLegal representante = new RepresentanteLegal();
            representante.setNombre(dto.getNombreRepresentante());
            representante.setCargo(dto.getCargoRepresentante());
            representante.setCorreo(dto.getCorreoRepresentante());
            representante.setTelefono(dto.getTelefonoRepresentante());
            representante = representanteLegalRepository.save(representante);

            // Crear la nueva empresa
            Empresa empresa = new Empresa();
            empresa.setRazon_social(dto.getNombreEmpresa());
            empresa.setRuc(dto.getRucEmpresa());
            empresa.setTelefono(dto.getTelefonoEmpresa());
            empresa.setCorreo(dto.getCorreoEmpresa());
            empresa.setDireccion(dto.getDireccionEmpresa());
            empresa.setRepresentanteLegal(representante);

            // Asignar el estado con un valor 'P' (representa "En Proceso")
            empresa.setEstado('P');  // Asignar el estado 'P' para "En Proceso"

            // Guardar la empresa
            empresaRepository.save(empresa);

            return ResponseEntity.ok("Empresa registrada exitosamente con estado 'En Proceso'");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la empresa: " + e.getMessage());
        }
    }
}
