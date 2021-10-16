package br.gov.sp.tce.concessoes.ppps.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.gov.sp.tce.concessoes.ppps.persistence.entity.BaseEntity;

public interface GenericRepository<E extends BaseEntity> {
    
    public Optional<E> findById(Serializable id);

    public List<E> findAll();

    public Optional<E> save(E concessao);

    public Optional<E> update(E concessao);

    public Optional<E> deleteById(Serializable id);
}
