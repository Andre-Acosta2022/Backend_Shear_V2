package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tipo_documento")
@CrossOrigin
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Long idTipoDocumento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "url")
    private String Url;

    @ManyToOne
    @JoinColumn(name = "id_rol_emisor", referencedColumnName = "id_rol", nullable = false)
    
    private Rol rol_emisor;

    @ManyToOne
    @JoinColumn(name = "id_rol_receptor", referencedColumnName = "id_rol", nullable = false)
    
    private Rol rol_receptor;

    @ManyToMany(mappedBy = "tipo_documento")
    @JsonIgnore
    private Set<PlanCarrera> plan_carrera = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "tipo_documento")
    @JsonIgnore
	private Set<Documentacion> documentacion;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "tipo_documento")
    @JsonIgnore
	private Set<Mensaje> mensaje;

}
