package br.edu.topicos.domain;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class DicaDeConsumo extends GenericDomain {
	private String titulo;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
