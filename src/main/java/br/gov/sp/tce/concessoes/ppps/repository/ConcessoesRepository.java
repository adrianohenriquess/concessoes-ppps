package br.gov.sp.tce.concessoes.ppps.repository;

import java.util.List;
import java.util.Optional;

import br.gov.sp.tce.concessoes.ppps.persistence.entity.Concessao;

public interface ConcessoesRepository {
    
    public Optional<Concessao> findById(Long id);

    public List<Concessao> findAll();

    public Optional<Concessao> save(Concessao concessao);

    public Optional<Concessao> update(Concessao concessao);

    public Optional<Concessao> deleteById(Long id);
}
