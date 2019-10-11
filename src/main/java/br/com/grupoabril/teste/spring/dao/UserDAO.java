package br.com.grupoabril.teste.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.grupoabril.teste.spring.models.Cliente;

@Repository("userDAO")
public class UserDAO { 
	
	@PersistenceContext
	private EntityManager em;

	public Cliente loadUserByUsername(Cliente cliente) {
		String jpql = "select c from Cliente c where c.email = :email and c.senha = :senha";
		TypedQuery<Cliente> query = em.createQuery(jpql,Cliente.class)
				.setParameter("email", cliente.getEmail())
				.setParameter("senha", cliente.getPassword());
		
		return query.getSingleResult();
	}
}