import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css'],
  imports: [CommonModule, FormsModule]
})
export class TransferenciaComponent {
  conta: string = '';
  agencia: string = '';
  tipoTransferencia: string = ''; 
  valor: number = 0;
  senha: string = '';
  mensagem: string = '';

  // Método para simular a transferência
  onSubmit() {
    if (this.conta && this.agencia && this.valor > 0 && this.senha) {
      this.mensagem = `Transferência de R$ ${this.valor.toFixed(2)} (${this.tipoTransferencia.toUpperCase()}) realizada com sucesso para a conta ${this.conta}, agência ${this.agencia}!`;
      this.resetForm();
    } else {
      this.mensagem = 'Por favor, preencha todos os campos corretamente.';
    }
  }

  // Método para limpar o formulário
  resetForm() {
    this.conta = '';
    this.agencia = '';
    this.tipoTransferencia = 'doc';
    this.valor = 0;
    this.senha = '';
  }
}
