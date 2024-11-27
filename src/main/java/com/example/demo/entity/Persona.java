package com.example.demo.entity;

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
@Table(name = "persona")
@CrossOrigin
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private Long idPersona;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellido", nullable = false)
	private String apellido;

	@Column(name = "email")
	private String email;
    @Column(name = "pais", length = 100)
    private String pais;
    
    @Column(name = "religion", length = 100)
    private String religion;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "dni", nullable = false, length = 8)
	private String dni;

	  @Column(name = "estado", length = 1)
	    private char estado;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
	private Set<Usuario> usuario;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
	private Set<Practicante> practicante;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
	private Set<Supervisor> supervisor;
}
