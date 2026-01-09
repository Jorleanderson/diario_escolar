const API_URL = "http://localhost:8080/api/professores";
let paginaAtual = 0;
let totalPaginas = 0;
const tamanhoPagina = 5;

function listarProfessoresPaginado(page = 0) {
    fetch(`${API_URL}/paginado?page=${page}&size=${tamanhoPagina}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar professores");
            }
            return response.json();
        })
        .then(data => {
            mostrarProfessores(data.content);
            console.log("DATA DA API:", data);

            paginaAtual = data.number;
            totalPaginas = data.totalPages;

            atualizarControles();
        })
        .catch(error => {
            console.error(error);
            alert("Erro ao carregar professores");
        });
}

function atualizarControles() {
    const info = document.getElementById("info-pagina");

    if (!info) return;

    info.textContent = `Página ${paginaAtual + 1} de ${totalPaginas}`;
}

function proximaPagina() {
    console.log("Clique em Próxima");

    if (paginaAtual < totalPaginas - 1) {
        listarProfessoresPaginado(paginaAtual + 1);
    }
}

function paginaAnterior() {
    console.log("Clique em Anterior");

    if (paginaAtual > 0) {
        listarProfessoresPaginado(paginaAtual - 1);
    }
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
        listarProfessoresPaginado(paginaAtual);
    })
    .catch(error => {
        console.error(error);
        alert(error.message || "Erro ao cadastrar professor");
    });
}