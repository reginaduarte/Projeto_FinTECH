import { CommonModule } from '@angular/common';
import { Component} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  imports: [CommonModule, FormsModule]
})
export class FormComponent {
  usuario = {
    idUsuario: null,
    cpf: '',
    nome: '',
    email: '',
    senha: '',
    tipoUsuario: '',
    dataCriacao: '',
    numeroConta: null
  };

  usuarios: any[] = [];
  editIndex: number | null = null;

  incluir(usuario: any) {
    if (this.editIndex !== null) {
      this.usuarios[this.editIndex] = { ...usuario };
      this.editIndex = null;
    } else {
      this.gerarIdUsuario(usuario);
      usuario.dataCriacao = new Date().toISOString();
      usuario.numeroConta = Math.floor(Math.random() * 1000000) + 1;
      this.usuarios.push({ ...usuario });
    }
    this.resetForm();
  }

  gerarIdUsuario(usuario: any) {
    // Gera um número aleatório único para o ID do usuário
    usuario.idUsuario = Math.floor(Math.random() * 1000000) + 1;
  }

  editarUsuario(index: number) {
    this.usuario = { ...this.usuarios[index] };
    this.editIndex = index;
  }

  deletarUsuario(index: number) {
    this.usuarios.splice(index, 1);
  }

  resetForm() {
    this.usuario = {
      idUsuario: null,
      cpf: '',
      nome: '',
      email: '',
      senha: '',
      tipoUsuario: '',
      dataCriacao: '',
      numeroConta: null
    };
    this.editIndex = null;
  }

  constructor(private router: Router) {}
  Limpar(){
    console.log('Limpar o formulário');
    this.resetForm();// Resetar o formulário após salvar
  }
  Visualizar() {
    throw new Error('.');
    }
  visualizar() {
    console.log('Vai para Visualização');
    this.router.navigate(['/visualizar']);
  }
  fechar() {
    console.log('Volta para adm');
    this.router.navigate(['/adm']);
  }
}
