package classesCadastro;

public class Cliente {
    private String nome;
    private String numeroDeCadastro;
    public enum TipoDeDocumento {
        CPF,
        CNPJ
    }
    public TipoDeDocumento tipoDoc;

    public Cliente(String nome, String numeroDeCadastro, TipoDeDocumento tipoDoc) {
        this.nome = nome;
        this.numeroDeCadastro = numeroDeCadastro;
        this.tipoDoc = tipoDoc;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroDeCadastro() {
        return numeroDeCadastro;
    }

    public TipoDeDocumento getTipoDoc() {
        return tipoDoc;
    }
}


