package br.com.grupoabril.teste.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.grupoabril.teste.spring.models.Produto;

@Repository
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Produto> list() {
		String jpql = "select p from Produto p";
		List<Produto> produto = em.createQuery(jpql, Produto.class).getResultList();
		return produto;
	}

	
}