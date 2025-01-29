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
  mensagem: string = '';
  idConta: number | null = null; 

  constructor(private atualizacaoService: AtualizacaoService) {}

  ngOnInit(): void {
    // Pegando o idConta do localStorage
    const idConta = localStorage.getItem('idConta');
    if (idConta) {
      this.idConta = Number(idConta); 
    } else {
      console.error('ID da conta não encontrado');
    }
  }

  // Método para atualizar os dados do usuário
  onSubmit() {
    if (this.telefone && this.email && this.idConta !== null) {
      const dadosAtualizados = {
        idUsuario: this.idConta, 
        telefoneUsuario: this.telefone,
        emailUsuario: this.email,
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
  }
}