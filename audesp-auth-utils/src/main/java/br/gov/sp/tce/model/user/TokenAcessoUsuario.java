package br.gov.sp.tce.model.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa um token de permissão dos usuários
 *
 * @author dvivencio
 */
public class TokenAcessoUsuario extends TokenInfoUsuario {
	
	private List<AcessoInterno> acessoInterno;
	
	private List<AcessoExterno> acessoExterno;
	
	private Set<String> papeis = new HashSet<>();
	
	public List<AcessoInterno> getAcessoInterno() {
		return acessoInterno;
	}
	
	public List<AcessoExterno> getAcessoExterno() {
		return acessoExterno;
	}
	
	public boolean isAcessoInterno() {
		return acessoInterno == null ? false : !acessoInterno.isEmpty();
	}
	
	public boolean isAcessoExterno() {
		return acessoExterno == null ? false : !acessoExterno.isEmpty();
	}
	
	public String[] getPapeis() {
		if (papeis.isEmpty()) {
			if (isAcessoInterno()) {
				for (AcessoInterno ai : acessoInterno) {
					papeis.add(ai.getCodigoPapel());
				}
			}
			if (isAcessoExterno()) {
				for (AcessoExterno ae : acessoExterno) {
					papeis.add(ae.getCodigoPapel());
				}
			}
		}
		return papeis.toArray(new String[] {});
	}
	
	public boolean hasPapel(String papel) {
		getPapeis();
		return (papeis.contains(papel));
	}
}
