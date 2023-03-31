package com.jeetemplates.app.interfaces.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeetemplates.app.domain.Endereco;
import com.jeetemplates.app.domain.EnderecoRepository;
import com.jeetemplates.app.domain.Pessoa;
import com.jeetemplates.app.domain.PessoaRepository;

@ManagedBean
public class EnderecoController {
	private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);
	
	private String estado;
	private String cidade;
	private String logradouro;
	private int numero;
	private String cep;
	private long idPessoa;
	private String nomePessoa;
	
	 private List<Endereco>enderecos;
	
	@ManagedProperty(value = "#{pessoaRepository}")
    private PessoaRepository pessoaRepository;
	
	@ManagedProperty(value = "#{enderecoRepository}")
    private EnderecoRepository enderecoRepository;
	
	private List<Pessoa> pessoas;
	
	private Pessoa pessoa;
	
	@PostConstruct
    public void init() {
        logger.debug("Lista Pessoa");
        this.pessoas = pessoaRepository.findAll();
        this.enderecos = enderecoRepository.findAll();    
    }
	
	
    public String carregar() {
        logger.debug("Lista Endereco");
        this.enderecos = enderecoRepository.findAll();   
        return null;
    }
    
    
    public String carregarPessoa() {
        logger.debug("Lista Endereco");
        pessoa = pessoaRepository.findOne(idPessoa);
        if (Objects.isNull(pessoa)) {
        	nomePessoa = "";
        }else {
        	nomePessoa = pessoa.getNome();
        	List<Endereco> le = new ArrayList<>();
        	try {
        		for (Endereco e:enderecos ) {
        			if(Objects.nonNull(e.getPessoa().getId())) {
        				if (e.getPessoa().getId() == idPessoa) {
        					le.add(e);
        				}
        			}
        		}    	
        		
        	}catch (Exception e) {
				// TODO: handle exception
			}
        	enderecos = le;
        	
        }        
        return null;
    }
    
    public String createpessoa() {      	
    	Endereco p= new Endereco();
    	this.pessoa.setId(this.idPessoa);
    	p.setPessoa(this.pessoa);
    	p.setLogradouro(getLogradouro());
    	p.setEstado(getEstado());
    	p.setNumero(getNumero());
    	p.setCidade(getCidade());
    	p.setCep(getCep());
    	
    	enderecoRepository.save(p);
        return "pessoa?faces-redirect=true";
    }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public PessoaRepository getPessoaRepository() {
		return pessoaRepository;
	}

	public void setPessoaRepository(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	public EnderecoRepository getEnderecoRepository() {
		return enderecoRepository;
	}

	public void setEnderecoRepository(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public long getIdPessoa() {
		return idPessoa;
	}


	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}


	public String getNomePessoa() {
		return nomePessoa;
	}


	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}	

}
