package br.edu.topicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.topicos.dao.DicaDeConsumoDAO;
import br.edu.topicos.domain.DicaDeConsumo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DicaDeConsumoBean implements Serializable {
	private DicaDeConsumo dica;
	private List<DicaDeConsumo> dicas;

	public DicaDeConsumo getDica() {
		return dica;
	}

	public void setDica(DicaDeConsumo dica) {
		this.dica = dica;
	}

	public List<DicaDeConsumo> getDicas() {
		return dicas;
	}

	public void setDicas(List<DicaDeConsumo> dicas) {
		this.dicas = dicas;
	}
	
	@PostConstruct
	public void listar(){
		try{
			DicaDeConsumoDAO dicaDAO = new DicaDeConsumoDAO();
			dicas = dicaDAO.listar();
		} catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as dicas");
		}
	}
	
	public void novo() {
		dica = new DicaDeConsumo();
	}
	
	public void salvar() {
		try {
			DicaDeConsumoDAO dicaDAO = new DicaDeConsumoDAO();
			dicaDAO.merge(dica);

			novo();
			dicas = dicaDAO.listar();

			Messages.addGlobalInfo("Dica de Consumo salva com sucesso");
		} catch (RuntimeException err) {
			Messages.addGlobalError("Erro ao tentar salvar");
			err.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
		dica = (DicaDeConsumo) evento.getComponent().getAttributes().get("dicaSelecionada");
		
		DicaDeConsumoDAO dicaDAO = new DicaDeConsumoDAO();
		dicaDAO.excluir(dica);
		
		dicas = dicaDAO.listar();
		
		Messages.addGlobalInfo("Dica de Usuário excluída com sucesso");
		} catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar remover a Dica de Consumo");
		}
	}
	
	public void editar(ActionEvent evento){
		dica = (DicaDeConsumo) evento.getComponent().getAttributes().get("dicaSelecionada");
	}

}
