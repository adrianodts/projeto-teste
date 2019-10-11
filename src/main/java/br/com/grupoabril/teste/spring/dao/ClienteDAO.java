package br.com.grupoabril.teste.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.grupoabril.teste.spring.models.Cliente;

@Repository("clienteDAO")
public class ClienteDAO {
	
	@PersistenceContext
	private EntityManager em;

		
	public List<Cliente> list() {
		String jpql = "select c from Cliente c";
		List<Cliente> clientes = em.createQuery(jpql, Cliente.class).getResultList();
		return clientes;
	}

	public Cliente find(Integer id) {		
		TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.codigoCliente =:id", Cliente.class)
				.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public long findDuplicate(Cliente cliente) {		
		TypedQuery<Long> count = em.createQuery("select count(c) from Cliente c where (c.documento =:documento or c.email = :email)", Long.class)
				.setParameter("documento", cliente.getDocumento())
				.setParameter("email", cliente.getEmail());
		return count.getSingleResult();
	}

	public void save(Cliente cliente) {
		em.persist(cliente);
	}

	public void delete(Integer id) {
		em.remove(find(id));
	}

	public void update(Cliente cliente) {
		em.merge(cliente);
	}

}