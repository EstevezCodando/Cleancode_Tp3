package br.com.empresa.gestaoagil.dominio;

import java.util.Objects;

/**
 * Value Object imutável que representa um resumo do progresso de uma sprint.
 */
public final class ResumoProgressoSprint {

    private final String nomeSprint;
    private final int totalTarefas;
    private final int tarefasConcluidas;
    private final double percentualConcluido;

    public ResumoProgressoSprint(
            String nomeSprint,
            int totalTarefas,
            int tarefasConcluidas,
            double percentualConcluido
    ) {
        this.nomeSprint = Objects.requireNonNull(nomeSprint, "Nome da sprint não pode ser nulo");
        this.totalTarefas = totalTarefas;
        this.tarefasConcluidas = tarefasConcluidas;
        this.percentualConcluido = percentualConcluido;
    }

    public String getNomeSprint() {
        return nomeSprint;
    }

    public int getTotalTarefas() {
        return totalTarefas;
    }

    public int getTarefasConcluidas() {
        return tarefasConcluidas;
    }

    public double getPercentualConcluido() {
        return percentualConcluido;
    }

    @Override
    public String toString() {
        return "ResumoProgressoSprint{" +
                "nomeSprint='" + nomeSprint + '\'' +
                ", totalTarefas=" + totalTarefas +
                ", tarefasConcluidas=" + tarefasConcluidas +
                ", percentualConcluido=" + percentualConcluido +
                '}';
    }
}
