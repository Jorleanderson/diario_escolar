# diario_escolar
Um simples diário escolar onde existem turmas que cotém alunos e um professor, neste diário será possível adicionar notas para os alunos em cada turma e determinar se foram aprovados ou reprovados. Somente o professor pode editar notas dos alunos, enquanto os alunos podem somente vizualizar.

O Figma foi utilizado para a abstração do domínio desta API, sendo útil na análise e projeto da solução.

## Diagrama de Classes (Domínio da API)
```mermaid
classDiagram
      class Aluno {
        +Long id
        +String nome
        +String matricula
        +LocalDate dataNascimento
        +String email
        +List~Turma~ turmas
        +List~Nota~ notas
    }

    class Professor {
        +Long id
        +String nome
        +String email
        +String disciplina
        +List~Turma~ turmasLecionadas
    }

   class Nota {
        +Long id
        +Aluno aluno
        +Turma turma
        +BigDecimal valor
        +String tipo
    }

    class Turma {
        +Long id
        +String codigo
        +String nome
        +String periodo
        +Professor professor
        +List~Aluno~ alunos
        +List~Nota~ notas
    }

    Aluno "0..*" -- "0..*" Turma : frequenta
    Professor "1" -- "0..*" Turma : leciona
    Turma "1" -- "0..*" Nota : possui
    Aluno "1" -- "0..*" Nota : tem
```

