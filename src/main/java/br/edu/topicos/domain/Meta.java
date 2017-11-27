package br.edu.topicos.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Meta extends GenericDomain {
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataInicial;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataFinal;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicial;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFinal;

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

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

	public Date getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

}
