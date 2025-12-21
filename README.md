# diario_escolar
Um simples diário escolar onde existem turmas que cotém alunos e um professor, neste diário será possível adicionar notas para os alunos em cada turma e determinar se foram aprovados ou reprovados. Somente o professor pode editar notas dos alunos, enquanto os alunos podem somente vizualizar.

O Figma foi utilizado para a abstração do domínio desta API, sendo útil na análise e projeto da solução.

## Diagrama de Classes (Domínio da API)
```mermaid
classDiagram
    class Turma {
        +Long id
        +String codigo
        +String nome
        +Integer anoLetivo
        +String turno
    }

    class Aluno {
        +Long id
        +String nome
        +String matricula
        +LocalDate dataNascimento
        +String email
    }

    class Professor {
        +Long id
        +String nome
        +String email
        +String registro
        +Boolean ativo
    }

    class Disciplina {
        +Long id
        +String nome
        +Integer cargaHoraria
    }

    class Nota {
        +Long id
        +Integer trimestre
        +BigDecimal valor
    }

    Turma "1" -- "N" Aluno : possui
    Turma "1" -- "N" Disciplina : contém
    Professor "1" -- "N" Disciplina : responsável
    Disciplina "1" -- "N" Nota : gera
    Aluno "1" -- "N" Nota : recebe

```

