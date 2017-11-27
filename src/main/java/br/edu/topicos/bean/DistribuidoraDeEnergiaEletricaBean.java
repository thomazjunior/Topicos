package br.edu.topicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.topicos.dao.DicaDeConsumoDAO;
import br.edu.topicos.dao.DistribuidoraDeEnergiaEletricaDAO;
import br.edu.topicos.domain.DicaDeConsumo;
import br.edu.topicos.domain.DistribuidoraDeEnergiaEletrica;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DistribuidoraDeEnergiaEletricaBean implements Serializable {
	private DistribuidoraDeEnergiaEletrica distribuidora;
	private List<DistribuidoraDeEnergiaEletrica> distribuidoras;
	
	public DistribuidoraDeEnergiaEletrica getDistribuidora() {
		return distribuidora;
	}
	public void setDistribuidora(DistribuidoraDeEnergiaEletrica distribuidora) {
		this.distribuidora = distribuidora;
	}
	public List<DistribuidoraDeEnergiaEletrica> getDistribuidoras() {
		return distribuidoras;
	}
	public void setDistribuidoras(List<DistribuidoraDeEnergiaEletrica> distribuidoras) {
		this.distribuidoras = distribuidoras;
	}
	
	@PostConstruct
	public void listar(){
		try{
			DistribuidoraDeEnergiaEletricaDAO distribuidoraDAO = new DistribuidoraDeEnergiaEletricaDAO();
			distribuidoras = distribuidoraDAO.listar();
		} catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as distribuidoras");
		}
	}
	
	public void novo() {
		distribuidora = new DistribuidoraDeEnergiaEletrica();
	}
	
	public void salvar() {
		try {
			DistribuidoraDeEnergiaEletricaDAO distribuidoraDAO = new DistribuidoraDeEnergiaEletricaDAO();
			distribuidoraDAO.merge(distribuidora);

			novo();
			distribuidoras = distribuidoraDAO.listar();

			Messages.addGlobalInfo("Distribuidora salva com sucesso");
		} catch (RuntimeException err) {
			Messages.addGlobalError("Erro ao tentar salvar");
			err.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
		distribuidora = (DistribuidoraDeEnergiaEletrica) evento.getComponent().getAttributes().get("distribuidoraSelecionada");
		
		DistribuidoraDeEnergiaEletricaDAO distribuidoraDAO = new DistribuidoraDeEnergiaEletricaDAO();
		distribuidoraDAO.excluir(distribuidora);
		
		distribuidoras = distribuidoraDAO.listar();
		
		Messages.addGlobalInfo("Dica de Usuário excluída com sucesso");
		} catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar remover a Dica de Consumo");
		}
	}
	
	public void editar(ActionEvent evento){
		distribuidora = (DistribuidoraDeEnergiaEletrica) evento.getComponent().getAttributes().get("distribuidoraSelecionada");
	}
}
