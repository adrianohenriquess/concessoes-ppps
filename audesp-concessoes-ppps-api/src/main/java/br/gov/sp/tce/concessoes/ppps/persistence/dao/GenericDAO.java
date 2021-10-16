package br.gov.sp.tce.concessoes.ppps.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.gov.sp.tce.concessoes.ppps.persistence.entity.BaseEntity;
import br.gov.sp.tce.concessoes.ppps.repository.GenericRepository;

public abstract class GenericDAO<E extends BaseEntity> implements GenericRepository<E> {

    @PersistenceContext
    protected EntityManager em;

    protected abstract Class<E> enitityClass();

    @Override
    public Optional<E> findById(Serializable id) {
        E entity  = em.find(enitityClass(), id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(enitityClass());
        Root<E> entity = cq.from(enitityClass());
        cq.select(entity);
        TypedQuery<E> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Optional<E> save(E entity) {
        E e = null;
        if (entity != null && entity.getId() == null) {
            em.persist(entity);
            e = entity;
        }
        return Optional.of(e);
    }

    @Override
    public Optional<E> update(E entity) {
        E e = null;
        if (this.findById(entity.getId()).isPresent()) {
            e = em.merge(entity);
        }
        return Optional.ofNullable(e);
    }

    @Override
    public Optional<E> deleteById(Serializable id) {
        Optional<E> concessao = this.findById(id);
        concessao.ifPresent(em::remove);
        return concessao;
    }
    
}
