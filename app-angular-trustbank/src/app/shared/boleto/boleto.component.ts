import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-boleto',
  templateUrl: './boleto.component.html',
  styleUrls: ['./boleto.component.css'],
  imports: [FormsModule, CommonModule]
})
export class BoletoComponent {
  // Variáveis que armazenam os dados do formulário
  codigoBarras: string = '';
  vencimento: string = '';
  valor: number = 0;
  senha: string = '';
  
  mensagem: string | null = null;

  // Método chamado quando o formulário é submetido
  onSubmit() {
    if (this.codigoBarras && this.vencimento && this.valor && this.senha) {
      // Mensagem de sucesso
      this.mensagem = 'Boleto processado com sucesso!';
    } else {
      // Mensagem de erro
      this.mensagem = 'Por favor, preencha todos os campos.';
    }

    // Limpa os campos após a submissão
    this.codigoBarras = '';
    this.vencimento = '';
    this.valor = 0;
    this.senha = '';
  }
}
