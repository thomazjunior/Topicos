package br.edu.topicos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Eletrodomestico extends GenericDomain {
	
	@Column(nullable = false)
	private String fabricante;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private int consumoMedio;
	
	@Transient
	private String caminho;

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(int consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	

}
