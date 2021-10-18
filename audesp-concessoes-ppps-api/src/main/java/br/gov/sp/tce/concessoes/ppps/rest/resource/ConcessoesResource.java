package br.gov.sp.tce.concessoes.ppps.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

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

import org.modelmapper.ModelMapper;

import br.gov.sp.tce.concessoes.ppps.persistence.entity.Concessao;
import br.gov.sp.tce.concessoes.ppps.repository.GenericRepository;
import br.gov.sp.tce.concessoes.ppps.rest.dto.ConcessaoDTO;

@Path("/concessoes")
@Singleton
public class ConcessoesResource {

    @Inject
    protected ModelMapper modelMapper;

    @Inject
    protected GenericRepository<Concessao> repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ConcessaoDTO criarConcessao(ConcessaoDTO concessaoDTO) {
        return repository.save(modelMapper.map(concessaoDTO, Concessao.class))
                .map((c)-> modelMapper.map(c, ConcessaoDTO.class))
                .orElseThrow(BadRequestException::new);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConcessaoDTO> listarConcessoes() {
        return repository.findAll().stream()
            .map((c) -> modelMapper.map(c, ConcessaoDTO.class))
            .collect(Collectors.toList());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public ConcessaoDTO alterarConcessao(ConcessaoDTO concessao) {
        Concessao entity = new Concessao(concessao.getId(), concessao.getDescricao(), 
        concessao.getDataInicial(), concessao.getDataFinal());
        return repository.update(entity)
            .map((c) -> modelMapper.map(c, ConcessaoDTO.class))
            .orElseThrow(NotFoundException::new);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public ConcessaoDTO removerConcessao(@PathParam("id") Long id) {
        return repository.deleteById(id)
            .map((c) -> modelMapper.map(c, ConcessaoDTO.class))
            .orElseThrow(NotFoundException::new);
    }
    
}
