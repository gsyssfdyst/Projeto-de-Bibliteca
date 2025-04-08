document.getElementById('form').addEventListener('submit', async (event) => {
    event.preventDefault(); // Impede o envio padrão do formulário

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries()); // Converte para um objeto

    try {
        const response = await fetch('/api/endpoint', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error('Erro na requisição: ' + response.statusText);
        }

        const result = await response.json();
        console.log('Sucesso:', result);
        // Adicione lógica adicional aqui, como exibir uma mensagem de sucesso
    } catch (error) {
        console.error('Erro:', error);
        // Adicione lógica adicional aqui, como exibir uma mensagem de erro
    }
});
