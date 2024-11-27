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
@Table(name = "empresa")
@CrossOrigin
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa")
	private Long idEmpresa;

	@Column(name = "razon_social", nullable = false)
	private String razon_social;

	@Column(name = "ruc", nullable = false)
	private String ruc;

	@Column(name = "correo", nullable = false)
	private String correo;

	@Column(name = "telefono", nullable = false)
	private String telefono;

	@Column(name = "direccion", nullable = false)
	private String direccion;
	@Column(name = "estado", length = 1, nullable = false)
	private char estado;
	@ManyToOne
	@JoinColumn(name = "id_representante", nullable = false)
	private RepresentanteLegal representanteLegal;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empresa")
	@JsonIgnore
	private Set<Practica> practica;
}
