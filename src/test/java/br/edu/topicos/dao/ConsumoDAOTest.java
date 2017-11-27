package br.edu.topicos.dao;

import java.text.DateFormat;
import java.text.ParseException;

import org.junit.Test;

import br.edu.topicos.domain.Consumo;

public class ConsumoDAOTest {

	@Test
	public void salvar() {
		Consumo consumo = new Consumo();
		DateFormat formata = DateFormat.getDateInstance();
		try {
			consumo.setDataInicio(formata.parse("10/10/1000"));
			consumo.setDataTermino(formata.parse("10/10/1000"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConsumoDAO consumoDAO = new ConsumoDAO();
		consumoDAO.salvar(consumo);
	}
	
	
}