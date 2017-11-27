package br.edu.topicos.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.topicos.domain.Eletrodomestico;

public class EletrodomesticoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Eletrodomestico elet = new Eletrodomestico();
		elet.setConsumoMedio(100);
		elet.setFabricante("Teste2");
		elet.setModelo("Test2");

		EletrodomesticoDAO eletrodomesticoDAO = new EletrodomesticoDAO();
		eletrodomesticoDAO.salvar(elet);
	}

	@Test
	@Ignore
	public void listar() {
		EletrodomesticoDAO eletrodomesticoDAO = new EletrodomesticoDAO();
		List<Eletrodomestico> resultado = eletrodomesticoDAO.listar();

		for (Eletrodomestico eletrodomestico : resultado)
			System.out.println(eletrodomestico.getConsumoMedio() + " - " + eletrodomestico.getFabricante());

	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		EletrodomesticoDAO eletrodomesticoDAO = new EletrodomesticoDAO();
		Eletrodomestico elet = eletrodomesticoDAO.buscar(codigo);

		System.out.println(elet.getConsumoMedio());
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		EletrodomesticoDAO eletDAO = new EletrodomesticoDAO();
		Eletrodomestico elet = eletDAO.buscar(codigo);
		eletDAO.excluir(elet);
	}

	@Test
	public void editar() {
		Long codigo = 4L;

		EletrodomesticoDAO eletDAO = new EletrodomesticoDAO();
		Eletrodomestico elet = eletDAO.buscar(codigo);
		
		elet.setConsumoMedio(200);
		eletDAO.editar(elet);
	}
}
