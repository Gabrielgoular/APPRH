package com.goulartDev.appRH.AppRH.model;

import java.io.Serializable;	
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotEmpty;
@Entity
public class Vaga implements Serializable  {
	
	private static final long serialVersionUID= 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao; 
	@NotEmpty
	private String data;
	@NotEmpty
	private String salario;
	
	@OneToMany(mappedBy = "vaga",cascade = CascadeType.REMOVE)
	private List<Candidato> candidatos;

	public Vaga() {
		super();
	}
	

	public Vaga(long codigo, String nome, String descricao, String data, String salario, List<Candidato> candidatos) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.salario = salario;
		this.candidatos = candidatos;
	}


	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	
	
	
	
	
}
