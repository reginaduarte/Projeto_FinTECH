import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html',
  styleUrls: ['./deposito.component.css'],
  imports: [FormsModule, CommonModule]
})
export class DepositoComponent {
  conta: string = '';
  agencia: string = '';
  valor: number = 0;
  senha: string = '';
  mensagem: string = '';

  // Método para simular o depósito
  onSubmit() {
    if (this.conta && this.agencia && this.valor > 0 && this.senha) {
      this.mensagem = `Depósito de R$ ${this.valor.toFixed(2)} realizado com sucesso para a conta ${this.conta}, agência ${this.agencia}!`;
      this.resetForm();
    } else {
      this.mensagem = 'Por favor, preencha todos os campos corretamente.';
    }
  }

  // Método para limpar o formulário
  resetForm() {
    this.conta = '';
    this.agencia = '';
    this.valor = 0;
    this.senha = '';
  }
}
