import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AtualizacaoService } from '../../services/atualizacao.service';

@Component({
  selector: 'app-edicao-dados',
  templateUrl: './configuracao.component.html',
  styleUrls: ['./configuracao.component.css'],
  imports: [FormsModule, CommonModule],
})
export class ConfiguracaoComponent implements OnInit {
  telefone: string = '';
  email: string = '';
  senha: string = '';
  mensagem: string = '';
  idUsuario: number | null = null; 

  constructor(private atualizacaoService: AtualizacaoService) {}

  ngOnInit(): void {
    const idUsuario = localStorage.getItem('idUsuario');
    if (idUsuario) {
      this.idUsuario = Number(idUsuario); 
    } else {
      console.error('ID do usuário não encontrado');
    }
  }

  // Método para atualizar os dados do usuário
  onSubmit() {
    if (this.telefone && this.email && this.idUsuario !== null) {
      const dadosAtualizados = {
        idUsuario: this.idUsuario, 
        telefoneUsuario: this.telefone,
        emailUsuario: this.email,
        senhaUsuario: this.senha
      };

      this.atualizacaoService.atualizarDados(dadosAtualizados).subscribe({
        next: () => {
          this.mensagem = `Dados pessoais atualizados com sucesso!`;
          this.resetForm();
        },
        error: (err) => {
          console.error('Erro ao atualizar dados:', err);
          this.mensagem = 'Erro ao atualizar dados. Tente novamente mais tarde.';
        },
      });
    } else {
      this.mensagem = 'Por favor, preencha todos os campos corretamente.';
    }
  }

  // Método para limpar o formulário
  resetForm() {
    this.telefone = '';
    this.email = '';
    this.senha = '';
  }
}