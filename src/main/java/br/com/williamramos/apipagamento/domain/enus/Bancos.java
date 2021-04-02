package br.com.williamramos.apipagamento.domain.enus;

public enum Bancos {
    BRADESCO("Bradesco", "237"),
    BB("Banco do Brasil", "001"),
    CAIXA_ECONOMICA("Caixa Economica", "102");

    private String nome;
    private String codigo;

    Bancos(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }
}
