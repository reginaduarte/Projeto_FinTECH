import { Component, OnInit } from '@angular/core';
import { AdmService } from '../../services/adm.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioEdicaoComponent } from "../usuarioedicao/usuarioedicao.component";
import { AtualizarService } from '../../services/atualizar.service';

@Component({
  selector: 'app-visualizar-usuarios',
  templateUrl: './visualizar.component.html',
  styleUrls: ['./visualizar.component.css'],
  imports: [CommonModule, FormsModule, UsuarioEdicaoComponent]
})
export class VisualizarComponent implements OnInit {
  usuarios: any[] = [];
  usuarioParaEdicao: any = null;

  constructor(private admService: AdmService, private atualizarService: AtualizarService) {}

  ngOnInit(): void {
    this.carregarUsuarios();
  }

  carregarUsuarios(): void {
    this.admService.listarClientes().subscribe({
      next: (dados) => {
        this.usuarios = dados;
      },
      error: (erro) => {
        console.error('Erro ao buscar usuários:', erro);
      }
    });
  }

  // Método para iniciar a edição do usuário
  editarUsuario(usuario: any): void {
    this.usuarioParaEdicao = { ...usuario }; 
  }

  // Método para atualizar os dados do usuário
  salvarEdicao(usuario: any): void {
    if (usuario) {
      this.atualizarService.atualizarDados(usuario).subscribe({
        next: (response) => {
          alert('Usuário atualizado com sucesso!');
          this.usuarioParaEdicao = null; 
          this.carregarUsuarios(); 
        },
        error: (erro) => {
          console.error('Erro ao atualizar usuário:', erro);
          alert('Erro ao atualizar usuário!');
        }
      });
    }
  }

  // Método para cancelar a edição
  cancelarEdicao(): void {
    this.usuarioParaEdicao = null; 
  }

  // Método para deletar o usuário
  deletarUsuario(cpf: string): void {
    if (confirm('Tem certeza que deseja excluir este usuário?')) {
      this.admService.deletarUsuario(cpf).subscribe({
        next: (response) => {
          alert('Usuário excluído com sucesso!');
          this.carregarUsuarios(); 
        },
        error: (erro) => {
          console.error('Erro ao excluir usuário:', erro);
          alert('Erro ao excluir usuário!');
        }
      });
    }
  }
}
