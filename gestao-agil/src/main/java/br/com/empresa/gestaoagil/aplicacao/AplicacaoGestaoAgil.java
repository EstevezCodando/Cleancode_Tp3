package br.com.empresa.gestaoagil.aplicacao;

import br.com.empresa.gestaoagil.dominio.*;

import java.time.LocalDate;

public class AplicacaoGestaoAgil {

    public static void main(String[] args) {
        // Cadastro de usuário
        Usuario desenvolvedora = new Usuario(
                "Maria Silva",
                "maria.silva@empresa.com",
                "Desenvolvedora Backend"
        );

        // Cadastro de tarefas
        Tarefa tarefa1 = new Tarefa(
                "Implementar cadastro de projetos",
                "Criar APIs REST para cadastro e consulta de projetos"
        );
        tarefa1.atribuirResponsavel(desenvolvedora);
        tarefa1.alterarStatus(StatusTarefa.DONE);

        Tarefa tarefa2 = new Tarefa(
                "Implementar cadastro de sprints",
                "Criar telas para cadastro e listagem de sprints"
        );
        tarefa2.atribuirResponsavel(desenvolvedora);
        tarefa2.alterarStatus(StatusTarefa.IN_PROGRESS);

        // Cadastro de sprint
        Sprint sprint1 = new Sprint(
                "Sprint 1",
                LocalDate.now(),
                LocalDate.now().plusDays(14)
        );
        sprint1.adicionarTarefa(tarefa1);
        sprint1.adicionarTarefa(tarefa2);

        // Cadastro de projeto (imutável)
        Projeto projeto = Projeto.novoProjeto(
                "Sistema de Gestão Ágil",
                "Sistema para acompanhar projetos, sprints e tarefas"
        );
        projeto = projeto.adicionarSprint(sprint1);

        // Cálculo do resumo de progresso da sprint (Exercício 2)
        ResumoProgressoSprint resumo = sprint1.calcularResumoProgresso();

        System.out.println("===== PROJETO =====");
        System.out.println(projeto);

        System.out.println("\n===== SPRINTS =====");
        projeto.listarSprints().forEach(sprint -> {
            System.out.println("Sprint: " + sprint.getNome());
            sprint.listarTarefas().forEach(tarefa ->
                    System.out.println("  - " + tarefa.exibirDetalhes())
            );
        });

        System.out.println("\n===== RESUMO DE PROGRESSO DA SPRINT =====");
        System.out.println(resumo);
    }
}
