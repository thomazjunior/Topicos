package br.edu.topicos.dao;

import org.junit.Test;

import br.edu.topicos.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	public void salvar() {
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setSenha("xxxx");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
}
