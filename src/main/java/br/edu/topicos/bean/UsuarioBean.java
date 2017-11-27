package br.edu.topicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.topicos.dao.UsuarioDAO;
import br.edu.topicos.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private List<Usuario> usuarios;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
		}
	}
	
	public void novo() {
		usuario = new Usuario();
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			novo();
			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo("Usuario salvo com sucesso");
		} catch (RuntimeException err) {
			Messages.addGlobalError("Erro ao tentar salvar");
			err.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
		
		usuarios = usuarioDAO.listar();
		
		Messages.addGlobalInfo("Usuario excluído com sucesso");
		} catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o usuário");
		}
	}
	
	public void editar(ActionEvent evento){
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
	}
}
