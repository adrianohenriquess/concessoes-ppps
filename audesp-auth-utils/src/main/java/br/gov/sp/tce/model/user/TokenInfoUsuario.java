package br.gov.sp.tce.model.user;

import java.util.Date;
import java.util.UUID;

public class TokenInfoUsuario {

    private UUID idUsuario;

    private String nome;

    private String cpfCnpj;

    private String email;

    private String loginLDAP;

    private String telefone;

    private Date dataNascimento;

    private String cnpjOrganizacao;

    private String nomeOrganizacao;
    
    private final String versao;

	public TokenInfoUsuario() {
		super();
		versao = "1.0";
	}

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginLDAP() {
        return loginLDAP;
    }

    public void setLoginLDAP(String loginLDAP) {
        this.loginLDAP = loginLDAP;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCnpjOrganizacao() {
        return cnpjOrganizacao;
    }

    public void setCnpjOrganizacao(String cnpjOrganizacao) {
        this.cnpjOrganizacao = cnpjOrganizacao;
    }

    public String getNomeOrganizacao() {
        return nomeOrganizacao;
    }

    public void setNomeOrganizacao(String nomeOrganizacao) {
        this.nomeOrganizacao = nomeOrganizacao;
    }

}
