package br.edu.topicos.domain;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class DistribuidoraDeEnergiaEletrica extends GenericDomain {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
