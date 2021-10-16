package br.gov.sp.tce.concessoes.ppps.persistence.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.gov.sp.tce.concessoes.ppps.persistence.entity.Concessao;

@Transactional
@ApplicationScoped
public class ConcessaoDAO extends GenericDAO<Concessao> {

    @Override
    protected Class<Concessao> enitityClass() {
        return Concessao.class;
    }

}
