package br.gov.sp.tce.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class InformacoesDelegacoesVO implements Serializable {

    private static final long serialVersionUID = -919155547801792963L;

    private UUID idUsuario;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String loginLDAP;
    private String telefone;
    private Date dataNascimento;
    private String cnpjOrganizacao;
    private String nomeOrganizacao;
    private String versao;

    private List<AcessoInterno> acessoInterno = new ArrayList<AcessoInterno>();

    private List<AcessoExterno> acessoExterno = new ArrayList<AcessoExterno>();

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

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

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

    @Override
    public String toString() {
        return "InformacoesDelegacoesVO [idUsuario=" + idUsuario + ", nome="
                + nome + ", cpfCnpj=" + cpfCnpj + ", email=" + email
                + ", loginLDAP=" + loginLDAP + ", telefone=" + telefone
                + ", dataNascimento=" + dataNascimento + ", cnpjOrganizacao="
                + cnpjOrganizacao + ", nomeOrganizacao=" + nomeOrganizacao
                + ", versao=" + versao + "]";
    }

}
