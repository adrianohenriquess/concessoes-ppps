package br.gov.sp.tce.concessoes.ppps.persistence.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.gov.sp.tce.concessoes.ppps.persistence.entity.Concessao;
import br.gov.sp.tce.concessoes.ppps.repository.ConcessoesRepository;

@Transactional
@ApplicationScoped
public class ConcessoesDAO implements ConcessoesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Concessao> findById(Long id) {
        Concessao c  = em.find(Concessao.class, id);
        return Optional.ofNullable(c);
    }

    @Override
    public List<Concessao> findAll() {
        TypedQuery<Concessao> query = em.createQuery("SELECT c FROM Concessao c", Concessao.class);
        return query.getResultList();
    }

    @Override
    public Optional<Concessao> save(Concessao concessao) {
        Concessao c = null;
        if (concessao != null && concessao.getId() == null) {
            em.persist(concessao);
            c = concessao;
        }
        return Optional.of(c);
    }

    @Override
    public Optional<Concessao> update(Concessao concessao) {
        Concessao c = null;
        if (this.findById(concessao.getId()).isPresent()) {
            c = em.merge(concessao);
        }
        return Optional.ofNullable(c);
    }

    @Override
    public Optional<Concessao> deleteById(Long id) {
        Optional<Concessao> concessao = this.findById(id);
        concessao.ifPresent(em::remove);
        return concessao;
    }
    
}
