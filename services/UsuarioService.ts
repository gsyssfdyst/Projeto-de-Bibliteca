import { Usuario } from '../models/usuario'; // Corrigido o caminho do import
import { UsuarioRepository } from '../repositories/usuarioRepository'; // Corrigido o caminho do import

export class UsuarioService {
  private usuarioRepository: UsuarioRepository;

  constructor() {
    this.usuarioRepository = new UsuarioRepository();
  }

  async criarUsuario(dados: Partial<Usuario>): Promise<Usuario> {
    // ...lógica de validação ou transformação de dados...
    return this.usuarioRepository.criar(dados);
  }

  async atualizarUsuario(id: string, dados: Partial<Usuario>): Promise<Usuario | null> {
    // ...lógica de validação ou transformação de dados...
    return this.usuarioRepository.atualizar(id, dados);
  }

  async excluirUsuario(id: string): Promise<boolean> {
    // ...lógica adicional, como verificar dependências...
    return this.usuarioRepository.excluir(id);
  }

  async buscarUsuarioPorId(id: string): Promise<Usuario | null> {
    return this.usuarioRepository.buscarPorId(id);
  }

  async listarUsuarios(): Promise<Usuario[]> {
    return this.usuarioRepository.listarTodos();
  }
}
