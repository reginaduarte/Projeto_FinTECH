import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edicao-dados',
  templateUrl: './configuracao.component.html',
  styleUrls: ['./configuracao.component.css'],
  imports: [FormsModule, CommonModule]
})
export class ConfiguracaoComponent {
  telefone: string = '';
  email: string = '';
  mensagem: string = '';

  // Método para simular a atualização dos dados
  onSubmit() {
    if (this.telefone && this.email) {
      this.mensagem = `Dados pessoais atualizados com sucesso!`;
      this.resetForm();
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
