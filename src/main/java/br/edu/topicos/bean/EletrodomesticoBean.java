package br.edu.topicos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.edu.topicos.dao.EletrodomesticoDAO;
import br.edu.topicos.domain.Eletrodomestico;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class EletrodomesticoBean implements Serializable {
	private Eletrodomestico eletro;
	private List<Eletrodomestico> eletros;

	public List<Eletrodomestico> getEletros() {
		return eletros;
	}

	public void setEletros(List<Eletrodomestico> eletros) {
		this.eletros = eletros;
	}

	public Eletrodomestico getEletro() {
		return eletro;
	}

	public void setEletro(Eletrodomestico eletro) {
		this.eletro = eletro;
	}

	public void novo() {
		eletro = new Eletrodomestico();
	}

	@PostConstruct
	public void listar() {
		try {
			EletrodomesticoDAO eletroDAO = new EletrodomesticoDAO();
			eletros = eletroDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar");
		}
	}

	public void salvar() {
		try {
			if (eletro.getCaminho() == null){
				Messages.addGlobalError("O campo foto é obrigatório");
				return;
			}
			EletrodomesticoDAO eletroDAO = new EletrodomesticoDAO();
			Eletrodomestico eletroRetorno = eletroDAO.merge(eletro);
			Path origem = Paths.get(eletro.getCaminho());
			Path destino = Paths.get("C:/Projeto/Uploads/Eletrodomesticos/" + eletroRetorno.getCodigo() + ".jpg");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			novo();
			eletros = eletroDAO.listar();

			Messages.addGlobalInfo("Eletrodoméstico salvo com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			eletro = (Eletrodomestico) evento.getComponent().getAttributes().get("eletrodomesticoSelecionado");

			EletrodomesticoDAO eletroDAO = new EletrodomesticoDAO();
			eletroDAO.excluir(eletro);
			
			Path arquivo = Paths.get("C:/Projeto/Uploads/Eletrodomesticos/" + eletro.getCodigo() + ".jpg");
			Files.deleteIfExists(arquivo);

			eletros = eletroDAO.listar();

			Messages.addGlobalInfo("Eletrodoméstico excluído com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o eletrodoméstico");
		}
	}

	public void editar(ActionEvent evento) {
		eletro = (Eletrodomestico) evento.getComponent().getAttributes().get("eletrodomesticoSelecionado");
		eletro.setCaminho("C:/Projeto/Uploads/Eletrodomesticos/" + eletro.getCodigo() + ".jpg");
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			eletro.setCaminho(arquivoTemp.toString());
			
			Messages.addGlobalInfo(eletro.getCaminho());
		} catch (IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar realizar o upload de arquivo");
		}
	}

}
