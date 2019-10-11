package br.com.grupoabril.teste.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.grupoabril.teste.spring.models.Assinatura;

@Repository
public class AssinaturaDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void save(Assinatura assinatura) {
		em.persist(assinatura);
	}

	public Assinatura findById(Integer id) {		
		TypedQuery<Assinatura> query = em.createQuery("SELECT a FROM Assinatura a WHERE a.codigoAssinatura = :id", Assinatura.class)
				.setParameter("id", id);
		return query.getSingleResult();
	}

	
	public List<Assinatura> list(Integer codigoCliente) {		
		TypedQuery<Assinatura> query = em.createQuery("SELECT a FROM Assinatura a INNER JOIN a.cliente c INNER JOIN a.produto p where c.codigoCliente = :codigoCliente", Assinatura.class)
				.setParameter("codigoCliente", codigoCliente);
		
		List<Assinatura> assinaturas = query.getResultList();
		return assinaturas;
	}

	public List<Assinatura> listAll() {		
		TypedQuery<Assinatura> query = em.createQuery("SELECT a FROM Assinatura a INNER JOIN a.cliente c INNER JOIN a.produto p", Assinatura.class);	
		List<Assinatura> assinaturas = query.getResultList();
		return assinaturas;
	}

	public void cancel(Integer id) {
		Assinatura assinatura = findById(id);
		assinatura.setAtivo(false);
		em.merge(assinatura);
	}
}