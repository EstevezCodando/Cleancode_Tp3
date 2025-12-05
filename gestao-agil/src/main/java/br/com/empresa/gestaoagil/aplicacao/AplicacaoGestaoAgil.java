package br.com.empresa.gestaoagil.aplicacao;

import br.com.empresa.gestaoagil.dominio.*;

import java.time.LocalDate;

public class AplicacaoGestaoAgil {

    public static void main(String[] args) {
        Projeto projeto = Projeto.novoProjeto(
                "Sistema de Gestão Ágil",
                "Sistema para acompanhar projetos, sprints e tarefas"
        );

        Sprint sprint1 = new Sprint(
                "Sprint 1",
                LocalDate.now(),
                LocalDate.now().plusDays(14)
        );

        Usuario desenvolvedora = new Usuario(
                "Maria Silva",
                "maria.silva@empresa.com",
                "Desenvolvedora Backend"
        );

        Tarefa tarefa1 = new Tarefa(
                "Implementar cadastro de projetos",
                "Criar APIs REST para cadastro e consulta de projetos"
        );
        tarefa1.atribuirResponsavel(desenvolvedora);
        tarefa1.alterarStatus(StatusTarefa.IN_PROGRESS);

        sprint1.adicionarTarefa(tarefa1);

        projeto = projeto.adicionarSprint(sprint1);

        System.out.println(projeto);
        projeto.listarSprints().forEach(sprint -> {
            System.out.println(" - " + sprint);
            sprint.listarTarefas().forEach(tarefa ->
                    System.out.println("   * " + tarefa.exibirDetalhes())
            );
        });
    }
}
