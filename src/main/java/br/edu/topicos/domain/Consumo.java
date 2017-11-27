package br.edu.topicos.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Consumo extends GenericDomain {
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Date dataTermino;
	
	double valorConsumido;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaTermino;
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	public double getValorConsumido() {
		return valorConsumido;
	}
	public void setValorConsumido(double valorConsumido) {
		this.valorConsumido = valorConsumido;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraTermino() {
		return horaTermino;
	}
	public void setHoraTermino(Date horaTermino) {
		this.horaTermino = horaTermino;
	}

	
	

}