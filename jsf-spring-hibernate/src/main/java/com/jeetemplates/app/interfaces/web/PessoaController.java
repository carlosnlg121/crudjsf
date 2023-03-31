package com.jeetemplates.app.interfaces.web;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeetemplates.app.application.HelloWorldService;
import com.jeetemplates.app.application.PessoaService;
import com.jeetemplates.app.domain.HelloWorld;
import com.jeetemplates.app.domain.HelloWorldRepository;
import com.jeetemplates.app.domain.Pessoa;
import com.jeetemplates.app.domain.PessoaRepository;

@ManagedBean
public class PessoaController {
	
	private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
	
	private String nome;
	private String sexo;
	private String idade;
	
	
	@ManagedProperty(value = "#{pessoaService}")
    private PessoaService pessoaService;

    @ManagedProperty(value = "#{pessoaRepository}")
    private PessoaRepository pessoaRepository; 
        
    private List<Pessoa> pessoas;
    
    @PostConstruct
    public void init() {
        logger.debug("Lista Pessoa");
        this.pessoas = pessoaRepository.findAll();
    }
    
    public String createpessoa() throws ParseException {    
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    	Pessoa p= new Pessoa();
    	p.setNome(this.getNome());
    	p.setSexo(this.getSexo());
    	p.setIdade(formato.parse(this.getIdade()));
    	pessoaService.create(p);
        return "pessoa?faces-redirect=true";
    }

	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public PessoaRepository getPessoaRepository() {
		return pessoaRepository;
	}

	public void setPessoaRepository(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}


}
