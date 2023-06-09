package com.jeetemplates.app.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeetemplates.app.domain.HelloWorld;
import com.jeetemplates.app.domain.Pessoa;
import com.jeetemplates.app.domain.PessoaRepository;

@Service("pessoaService")
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Pessoa create(Pessoa entity) {
        return pessoaRepository.save(entity);
    }

}
