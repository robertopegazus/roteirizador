package br.com.tutorial.pessoaDao;

import java.util.List;

import br.com.tutorial.classes.Origem;
import br.com.tutorial.dao.BaseDAO;

public class NodesDao extends BaseDAO<Origem> {
	public List<Origem> findAll() {
		return findAll(Origem.class);
	}
	
	public List<Origem> findByNome(String nome){
		return findByNome(nome, Origem.class);
	}
}