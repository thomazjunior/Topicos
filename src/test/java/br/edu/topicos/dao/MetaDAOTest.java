package br.edu.topicos.dao;

import java.text.DateFormat;
import java.text.ParseException;

import org.junit.Test;

import br.edu.topicos.domain.Meta;

public class MetaDAOTest {

	@Test
	public void salvar() {
		Meta meta = new Meta();
		DateFormat formata = DateFormat.getDateInstance();
		try {
			meta.setDataInicial(formata.parse("10/10/1000"));
			meta.setDataFinal(formata.parse("10/10/1000"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		meta.setDescricao("aaaa");
		meta.setTitulo("aaaa");

		MetaDAO metaDAO = new MetaDAO();
		metaDAO.salvar(meta);
	}
	
	
}
