package br.edu.topicos.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.edu.topicos.dao.ConsumoDAO;
import br.edu.topicos.domain.Consumo;
import br.edu.topicos.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ConsumoBean implements Serializable {
	private Consumo consumo;
	private List<Consumo> consumos;

	public Consumo getConsumo() {
		return consumo;
	}

	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}

	public List<Consumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	@PostConstruct
	public void listar() {
		try {
			ConsumoDAO consumoDAO = new ConsumoDAO();
			consumos = consumoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os consumos");
		}
	}

	public void novo() {
		consumo = new Consumo();
	}

	public void salvar() {
		try {
			ConsumoDAO consumoDAO = new ConsumoDAO();
			consumoDAO.merge(consumo);

			novo();
			consumos = consumoDAO.listar();

			Messages.addGlobalInfo("Consumo salva com sucesso");
		} catch (RuntimeException err) {
			Messages.addGlobalError("Erro ao tentar salvar");
			err.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			consumo = (Consumo) evento.getComponent().getAttributes().get("consumoSelecionado");

			ConsumoDAO consumoDAO = new ConsumoDAO();
			consumoDAO.excluir(consumo);

			consumos = consumoDAO.listar();

			Messages.addGlobalInfo("Consumo excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o Consumo");
		}
	}

	public void editar(ActionEvent evento) {
		consumo = (Consumo) evento.getComponent().getAttributes().get("consumoSelecionado");
	}

	public void imprimir() {
		try {
			String caminho = Faces.getRealPath("//reports//consumo.jasper");

			Map<String, Object> parametros = new HashMap<>();

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
}
