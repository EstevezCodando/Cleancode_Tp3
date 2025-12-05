package br.com.empresa.gestaoagil.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entidade central do domínio, modelada de forma imutável.
 * Representa um Projeto Ágil com sprints associadas.
 */
public final class Projeto {

    private final String idProjeto;
    private final String nome;
    private final String descricao;
    private final StatusProjeto status;
    private final LocalDate dataCriacao;
    private final List<Sprint> sprints;

    private Projeto(
            String idProjeto,
            String nome,
            String descricao,
            StatusProjeto status,
            LocalDate dataCriacao,
            List<Sprint> sprints
    ) {
        this.idProjeto = Objects.requireNonNull(idProjeto, "Id do projeto não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "Nome do projeto não pode ser nulo");
        this.descricao = Objects.requireNonNull(descricao, "Descrição do projeto não pode ser nula");
        this.status = Objects.requireNonNull(status, "Status do projeto não pode ser nulo");
        this.dataCriacao = Objects.requireNonNull(dataCriacao, "Data de criação não pode ser nula");

        List<Sprint> copiaSprints = (sprints == null) ? List.of() : new ArrayList<>(sprints);
        this.sprints = Collections.unmodifiableList(copiaSprints);
    }

    public static Projeto novoProjeto(String nome, String descricao) {
        String idGerado = UUID.randomUUID().toString();
        return new Projeto(
                idGerado,
                nome,
                descricao,
                StatusProjeto.ATIVO,
                LocalDate.now(),
                List.of()
        );
    }

    public String getIdProjeto() {
        return idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public List<Sprint> listarSprints() {
        return sprints;
    }

    public Projeto adicionarSprint(Sprint novaSprint) {
        Objects.requireNonNull(novaSprint, "Sprint não pode ser nula");
        List<Sprint> novaLista = new ArrayList<>(this.sprints);
        novaLista.add(novaSprint);
        return new Projeto(
                this.idProjeto,
                this.nome,
                this.descricao,
                this.status,
                this.dataCriacao,
                novaLista
        );
    }

    public Projeto removerSprint(Sprint sprintParaRemover) {
        Objects.requireNonNull(sprintParaRemover, "Sprint não pode ser nula");
        List<Sprint> novaLista = new ArrayList<>(this.sprints);
        novaLista.remove(sprintParaRemover);
        return new Projeto(
                this.idProjeto,
                this.nome,
                this.descricao,
                this.status,
                this.dataCriacao,
                novaLista
        );
    }

    public Projeto alterarStatus(StatusProjeto novoStatus) {
        Objects.requireNonNull(novoStatus, "Novo status não pode ser nulo");
        return new Projeto(
                this.idProjeto,
                this.nome,
                this.descricao,
                novoStatus,
                this.dataCriacao,
                this.sprints
        );
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "idProjeto='" + idProjeto + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", dataCriacao=" + dataCriacao +
                ", quantidadeSprints=" + sprints.size() +
                '}';
    }
}
