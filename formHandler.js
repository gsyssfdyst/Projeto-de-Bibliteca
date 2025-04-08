// Função para lidar com o envio do formulário
async function handleFormSubmit(event) {
    event.preventDefault(); // Previne o comportamento padrão do formulário

    const form = event.target;
    const formData = new FormData(form);

    // Converte os dados do formulário para um objeto JSON
    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });

    try {
        // Envia uma requisição POST para a API do backend
        const response = await fetch('/api/endpoint', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error('Erro ao enviar o formulário');
        }

        const result = await response.json();
        console.log('Sucesso:', result);
        alert('Formulário enviado com sucesso!');
    } catch (error) {
        console.error('Erro:', error);
        alert('Ocorreu um erro ao enviar o formulário.');
    }
}

// Exporta a função para ser usada em outros arquivos
export default handleFormSubmit;
