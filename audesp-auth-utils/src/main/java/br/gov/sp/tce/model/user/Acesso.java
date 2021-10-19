package br.gov.sp.tce.model.user;

public abstract class Acesso {
	
	private String codigoPapel;
	
	private transient String descricaoPapel;
	
	public String getCodigoPapel() {
		return codigoPapel;
	}

	public void setCodigoPapel(String codigoPapel) {
		this.codigoPapel = codigoPapel;
	}

	public String getDescricaoPapel() {
		return descricaoPapel;
	}

	public void setDescricaoPapel(String descricaoPapel) {
		this.descricaoPapel = descricaoPapel;
	}

}