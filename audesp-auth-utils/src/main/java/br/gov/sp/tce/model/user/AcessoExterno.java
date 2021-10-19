package br.gov.sp.tce.model.user;

public class AcessoExterno extends Acesso {

    private String codigoOrgao;

    private transient String nomeOrgao;

    private transient String codigoOrgaoMainFrame;

    public String getCodigoOrgaoMainFrame() {
        return codigoOrgaoMainFrame;
    }

    public void setCodigoOrgaoMainFrame(String codigoOrgaoMainFrame) {
        this.codigoOrgaoMainFrame = codigoOrgaoMainFrame;
    }

    public String getNomeOrgao() {
        return nomeOrgao;
    }

    public void setNomeOrgao(String nomeOrgao) {
        this.nomeOrgao = nomeOrgao;
    }

    public String getCodigoOrgao() {
        return codigoOrgao;
    }

    public void setCodigoOrgao(String codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }

}
