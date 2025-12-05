package br.com.empresa.gestaoagil.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Sprint {

    private final String nome;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final List<Tarefa> tarefas;

    public Sprint(String nome, LocalDate dataInicio, LocalDate dataFim) {
        this.nome = Objects.requireNonNull(nome, "Nome da sprint não pode ser nulo");
        this.dataInicio = Objects.requireNonNull(dataInicio, "Data de início não pode ser nula");
        this.dataFim = Objects.requireNonNull(dataFim, "Data de fim não pode ser nula");

        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("Data de fim não pode ser anterior à data de início");
        }

        this.tarefas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(Objects.requireNonNull(tarefa, "Tarefa não pode ser nula"));
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return Collections.unmodifiableList(tarefas);
    }

    /**
     * Método puro: calcula o resumo do progresso da sprint
     * sem alterar nenhum estado interno.
     */
    public ResumoProgressoSprint calcularResumoProgresso() {
        int total = tarefas.size();
        int concluidas = (int) tarefas.stream()
                .filter(t -> t.getStatus() == StatusTarefa.DONE)
                .count();

        double percentual = (total == 0) ? 0.0 : (concluidas * 100.0) / (double) total;

        return new ResumoProgressoSprint(
                this.nome,
                total,
                concluidas,
                percentual
        );
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", quantidadeTarefas=" + tarefas.size() +
                '}';
    }
}
