package br.com.empresa.gestaoagil.dominio;

import java.util.Objects;

public class Tarefa {

    private final String titulo;
    private final String descricao;
    private StatusTarefa status;
    private Usuario responsavel;

    public Tarefa(String titulo, String descricao) {
        this.titulo = Objects.requireNonNull(titulo, "Título não pode ser nulo");
        this.descricao = Objects.requireNonNull(descricao, "Descrição não pode ser nula");
        this.status = StatusTarefa.TODO;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void atribuirResponsavel(Usuario responsavel) {
        this.responsavel = Objects.requireNonNull(responsavel, "Responsável não pode ser nulo");
    }

    public void alterarStatus(StatusTarefa novoStatus) {
        this.status = Objects.requireNonNull(novoStatus, "Status não pode ser nulo");
    }

    public String exibirDetalhes() {
        String nomeResponsavel = (responsavel != null) ? responsavel.getNome() : "Não atribuído";
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", responsavel=" + nomeResponsavel +
                '}';
    }

    @Override
    public String toString() {
        return exibirDetalhes();
    }
}
