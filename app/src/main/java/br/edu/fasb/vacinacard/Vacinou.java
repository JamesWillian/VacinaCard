package br.edu.fasb.vacinacard;

public class Vacinou {
    private String Campanha;
    private String Vacinado;
    private String Data;
    private String Local;
    private String ProximaDose;

    public Vacinou(String campanha, String vacinado, String data, String local, String proximaDose){
        this.Campanha = campanha;
        this.Vacinado = vacinado;
        this.Data = data;
        this.Local = local;
        this.ProximaDose = proximaDose;
    }

    public String getCampanha() {
        return Campanha;
    }

    public void setCampanha(String campanha) {
        Campanha = campanha;
    }

    public String getVacinado() {
        return Vacinado;
    }

    public void setVacinado(String vacinado) {
        Vacinado = vacinado;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getProximaDose() {
        return ProximaDose;
    }

    public void setProximaDose(String proximaDose) {
        ProximaDose = proximaDose;
    }

    @Override
    public String toString() {
        return this.Vacinado;
    }
}
