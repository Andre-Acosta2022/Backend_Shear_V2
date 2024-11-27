package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "documentacion")
@CrossOrigin
public class Documentacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documentacion")
	private Long idDocumentacion;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "url")
	private String url;

	@Column(name = "fecha_subida")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaSubida;

	@Column(name = "estado", length = 1)
	private char estado;
	@ManyToOne
	@JoinColumn(name = "id_practica", nullable = false)

	private Practica practica;

	@ManyToOne
	@JoinColumn(name = "id_tipo_documento", nullable = false)

	private TipoDocumento tipo_documento;
}
