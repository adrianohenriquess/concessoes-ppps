package br.gov.sp.tce.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public final class PerfilVO implements Serializable, Comparable<PerfilVO> {

	private static final long serialVersionUID = -3655782099628071530L;

	private Integer id;
	private String nome;
	private String codigoPapel;

	// para usuarios externos codigo Orgao
	// para usuario internos codigo Grupo
	private Set<String> codigosAcessos = new HashSet<String>();

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
	
	public String getCodigoPapel() {
		return codigoPapel;
	}

	public void setCodigoPapel(String codigoPapel) {
		this.codigoPapel = codigoPapel;
	}

	public Set<String> getCodigosAcessos() {
		return codigosAcessos;
	}

	public void setCodigosAcessos(Set<String> codigosAcessos) {
		this.codigosAcessos = codigosAcessos;
	}

	public boolean isAdministrador(){
		return false;
	}
	
	public int compareTo(PerfilVO o) {
		if(o == null) return -1;
		if(o.getNome() == null) return -1;
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPapel == null) ? 0 : codigoPapel.hashCode());
		result = prime * result
				+ ((codigosAcessos == null) ? 0 : codigosAcessos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilVO other = (PerfilVO) obj;
		if (codigoPapel == null) {
			if (other.codigoPapel != null)
				return false;
		} else if (!codigoPapel.equals(other.codigoPapel))
			return false;
		if (codigosAcessos == null) {
			if (other.codigosAcessos != null)
				return false;
		} else if (!codigosAcessos.equals(other.codigosAcessos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PerfilVO [id=" + id + ", nome=" + nome + ", codigoAcesso="
				+ codigosAcessos + "]";
	}	

}
