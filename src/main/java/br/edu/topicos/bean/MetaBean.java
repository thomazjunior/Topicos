package br.edu.topicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.topicos.dao.MetaDAO;
import br.edu.topicos.domain.Meta;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MetaBean implements Serializable {
	private Meta meta;
	private List<Meta> metas;

	public List<Meta> getMetas() {
		return metas;
	}
	
	public void setMetas(List<Meta> metas) {
		this.metas = metas;
	}
	
	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public void novo() {
		meta = new Meta();
	}
	
	@PostConstruct
	public void listar(){
		try {
			MetaDAO metaDAO = new MetaDAO();
			metas = metaDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar as metas");
		}
	}

	public void salvar() {
		try {
			MetaDAO metaDAO = new MetaDAO();
			metaDAO.merge(meta);

			novo();
			metas = metaDAO.listar();

			Messages.addGlobalInfo("Meta salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			meta = (Meta) evento.getComponent().getAttributes().get("metaSelecionada");
			
			MetaDAO metaDAO = new MetaDAO();
			metaDAO.excluir(meta);
			
			metas = metaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir a meta");
		}
	}
	
	public void editar (ActionEvent evento){
		meta = (Meta) evento.getComponent().getAttributes().get("metaSelecionada");
	}

}
