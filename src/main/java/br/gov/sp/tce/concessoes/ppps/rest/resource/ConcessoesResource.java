package br.gov.sp.tce.concessoes.ppps.rest.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.gov.sp.tce.concessoes.ppps.persistence.entity.Concessao;
import br.gov.sp.tce.concessoes.ppps.repository.ConcessoesRepository;
import br.gov.sp.tce.concessoes.ppps.rest.dto.ConcessaoDTO;

/**
 *
 */
@Path("/concessoes")
@Singleton
public class ConcessoesResource {

    @Inject
    private ConcessoesRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Concessao criarConcessao(ConcessaoDTO concessao) {
        return repository.save(new Concessao(concessao.getId(), concessao.getDescricao(), 
            concessao.getDataInicial(), concessao.getDataFinal())).orElseThrow(BadRequestException::new);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Concessao> listarConcessoes() {
        return repository.findAll();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public ConcessaoDTO update(ConcessaoDTO concessao) {
        Concessao entity = new Concessao(concessao.getId(), concessao.getDescricao(), 
        concessao.getDataInicial(), concessao.getDataFinal());
        return repository.update(entity)
            .map((c) -> new ConcessaoDTO(c.getId(), c.getDescricao(), 
                c.getDataInicial(), c.getDataFinal()))
            .orElseThrow(NotFoundException::new);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public ConcessaoDTO delete(@PathParam("id") Long id) {
        return repository.deleteById(id)
            .map((c) -> new ConcessaoDTO(c.getId(), c.getDescricao(), 
                c.getDataInicial(), c.getDataFinal()))
            .orElseThrow(NotFoundException::new);
    }
    
}
