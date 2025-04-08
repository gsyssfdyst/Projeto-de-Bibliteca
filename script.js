async function fetchUsers() {
    try {
        const response = await fetch('https://api.example.com/users'); // Substitua pela URL do seu backend
        if (!response.ok) {
            throw new Error('Erro ao buscar usuários');
        }
        const users = await response.json();
        renderUsers(users);
    } catch (error) {
        console.error('Erro:', error);
    }
}

function renderUsers(users) {
    const tableBody = document.getElementById('user-table-body');
    tableBody.innerHTML = ''; // Limpa a tabela antes de renderizar
    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Chama a função para buscar e exibir os usuários ao carregar a página
document.addEventListener('DOMContentLoaded', fetchUsers);
