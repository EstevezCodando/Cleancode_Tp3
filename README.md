# TP3

## Exercicio 1

### Imutabilidade da Entidade Projeto e Benefícios para Concorrência

A entidade central escolhida no Sistema de Gestão de Projetos Ágil foi a classe **`Projeto`**
https://github.com/EstevezCodando/Cleancode_Tp3/blob/main/gestao-agil/src/main/java/br/com/empresa/gestaoagil/dominio/Projeto.java
que representa o agregado principal e controla o ciclo de vida de sprints e seu próprio status. Ela foi modelada como **imutável**, aplicando princípios de Clean Code e SOLID, em especial o _Single Responsibility Principle_ e o _Open-Closed Principle_.

### Ex.1.1. Atributos Imutáveis

Todos os atributos são declarados como `private final`, garantindo que não possam ser reatribuídos após a construção do objeto.

```java
private final String idProjeto;
private final String nome;
private final String descricao;
private final StatusProjeto status;
private final LocalDate dataCriacao;
private final List<Sprint> sprints;
```

Isso significa que, uma vez criado, o estado interno da entidade não sofre mutação.

### Ex.1.2. Construtor Privado e Cópias Defensivas

O construtor recebe uma lista de sprints e **cria uma cópia imutável**, evitando que referências externas modifiquem seu conteúdo.

```java
List<Sprint> copiaSprints = (sprints == null) ? List.of() : new ArrayList<>(sprints);
this.sprints = Collections.unmodifiableList(copiaSprints);
```

Esse padrão impede alterações acidentais no conteúdo da entidade por código externo.

### Ex.1.3. Ausência de Setters

Nenhum método na classe altera seus campos. Não existem métodos do tipo:

```java
public void setNome(String nome) { ... }  // não existe
```

Isso reforça que o estado é imutável.

### Ex.1.4. Métodos que “Alteram” o Estado Retornam uma Nova Instância

Quando é necessário representar uma modificação, como adicionar uma sprint ou alterar o status, a classe retorna **um novo Projeto**.

#### Exemplo: adicionar sprint

```java
public Projeto adicionarSprint(Sprint novaSprint) {
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
```

A instância original permanece inalterada.

#### Exemplo: alterar status

```java
public Projeto alterarStatus(StatusProjeto novoStatus) {
    return new Projeto(
        this.idProjeto,
        this.nome,
        this.descricao,
        novoStatus,
        this.dataCriacao,
        this.sprints
    );
}
```

Mais uma vez, nenhuma mutação ocorre.

### Ex.1.5. Por que isso evita problemas de concorrência?

Quando múltiplos usuários ou threads acessam simultaneamente um objeto imutável, nenhuma delas pode alterar seu estado interno. Assim, todos veem sempre a mesma versão do objeto, eliminando problemas clássicos como:

- **race conditions**, em que duas threads disputam a modificação do mesmo objeto;
- **estados intermediários inconsistentes**, causados por mutação parcial;
- **necessidade de sincronização complexa**, reduzindo o acoplamento e a possibilidade de deadlocks.

Como cada atualização gera uma nova instância, cada thread trabalha com uma **versão coerente**, e a aplicação decide posteriormente qual versão deve prevalecer (via serviços ou repositórios).

#### Exemplo Conceitual

Thread A:

```java
Projeto atualizado = projeto.alterarStatus(StatusProjeto.CONCLUIDO);
```

Thread B ao mesmo tempo:

```java
Projeto comNovaSprint = projeto.adicionarSprint(sprintX);
```

Ambas operam sobre o mesmo `projeto` original, mas produzem instâncias **independentes**, sem risco de interferência.

### Ex.1.6. Benefícios

- Facilita depuração e testes.
- Garante consistência interna forte.
- Reduz necessidade de bloqueios.
- Torna o fluxo de dados previsível e seguro.
- Segue boas práticas de modelagem funcional e SOLID.
