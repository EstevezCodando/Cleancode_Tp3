package br.com.empresa.gestaoagil.dominio;

import java.util.Objects;

public class Usuario {

    private String nome;
    private String email;
    private String cargo;

    public Usuario(String nome, String email, String cargo) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.email = Objects.requireNonNull(email, "E-mail não pode ser nulo");
        this.cargo = Objects.requireNonNull(cargo, "Cargo não pode ser nulo");
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public void atualizarEmail(String novoEmail) {
        this.email = Objects.requireNonNull(novoEmail, "Novo e-mail não pode ser nulo");
    }

    public void definirCargo(String novoCargo) {
        this.cargo = Objects.requireNonNull(novoCargo, "Novo cargo não pode ser nulo");
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
