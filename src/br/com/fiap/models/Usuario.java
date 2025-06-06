package br.com.fiap.models;

public class Usuario {
    private String nome;
    private String email;
    private String endereco;

    // Construtor com validação do e-mail
    public Usuario(String nome, String email, String endereco) {
        this.nome = nome;
        setEmail(email); // usa o setter para validar o e-mail
        this.endereco = endereco;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido. Deve conter '@'.");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Método para exibir informações do usuário
    public String exibirInformacoes() {
        return "Usuário: " + nome + ", Email: " + email + ", Endereço: " + endereco;
    }
}