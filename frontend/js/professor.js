const API_URL = "http://localhost:8080/api/professores";

function listarProfessores() {
    fetch(API_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar professores");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            mostrarProfessores(data);
        })
        .catch(error => {
            console.error(error);
            alert("Erro ao carregar professores");
        });
}

function mostrarProfessores(professores) {
    const lista = document.getElementById("lista-professores");
    lista.innerHTML = "";

    professores.forEach(prof => {
        const li = document.createElement("li");

        li.innerHTML = `
            <strong>Nome:</strong> ${prof.nome}<br>
            <strong>Email:</strong> ${prof.email}<br>
            <strong>Disciplina:</strong> ${prof.disciplina}<br>
            <strong>Formação:</strong> ${prof.formacao}
            <hr>
        `;

        lista.appendChild(li);
    });
}


function salvarProfessor(event) {
    event.preventDefault(); // impede recarregamento da página

   const professor = {
    nome: document.getElementById("nome").value,
    email: document.getElementById("email").value,
    disciplina: document.getElementById("disciplina").value,
    formacao: document.getElementById("formacao").value
};


    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(professor)
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(err => {
                throw err;
            });
        }
        return response.json();
    })
    .then(data => {
        alert("Professor cadastrado com sucesso!");
        document.getElementById("form-professor").reset();
        listarProfessores(); // atualiza lista
    })
    .catch(error => {
        console.error(error);
        alert(error.message || "Erro ao cadastrar professor");
    });
}