package br.gov.sp.tce.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class UsuarioDelegacoesVO implements Serializable {

	private static final long serialVersionUID = 4336888283864574561L;

	private Integer id;
	private String nome;
	private List<PerfilVO> perfis = new ArrayList<PerfilVO>();
	private InformacoesDelegacoesVO informacoesDelegacoes = new InformacoesDelegacoesVO();
	private Boolean acessoInterno;
	private Integer sistemaId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PerfilVO> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilVO> perfis) {
		this.perfis = perfis;
	}

	public InformacoesDelegacoesVO getInformacoesDelegacoes() {
		return informacoesDelegacoes;
	}

	public void setInformacoesDelegacoes(InformacoesDelegacoesVO informacoesDelegacoes) {
		this.informacoesDelegacoes = informacoesDelegacoes;
	}	
	
	public Boolean getAcessoInterno() {
		return acessoInterno;
	}

	public void setAcessoInterno(Boolean acessoInterno) {
		this.acessoInterno = acessoInterno;
	}

	public boolean temMaisDeUmPerfil(){
		return (perfis != null && perfis.size() > 1);
	}

	public Integer getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Integer codigoSistema) {
		this.sistemaId = codigoSistema;
	}

	/**
	 * Retorna null se n�o encontrar o perfil
	 * 
	 * @param perfilId
	 * @return
	 */
	public PerfilVO getPerfilById(Integer perfilId){
		for(PerfilVO pb : perfis){
			if(pb.getId().equals(perfilId)){
				return pb;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "UsuarioDelegacoesVO [id=" + id + ", nome=" + nome + ", perfis="
				+ perfis + ", informacoesDelegacoes=" + informacoesDelegacoes
				+ ", acessoInterno=" + acessoInterno + "]";
	}
}
