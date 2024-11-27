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
import jakarta.persistence.JoinTable;
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
@Table(name = "usuario")
@CrossOrigin
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "clave")
    private String clave;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @Column(name="estado", length = 1)
	private char estado;
	

    @OneToMany(mappedBy = "usuario_recibe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Notificaciones> notificaciones_recibe = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario_envia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
    private Set<Notificaciones> notificaciones_envia = new HashSet<>();
	
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "usuario_rol",
	        joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
	        inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
	    )
	    private Set<Rol> roles = new HashSet<>();

}
