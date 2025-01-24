import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-saque',
  imports: [FormsModule, CommonModule],
  templateUrl: './saque.component.html',
  styleUrls: ['./saque.component.css']
})
export class SaqueComponent {
  senha: string = '';
  valor: number | null = null;
  mensagem: string = '';

  onSubmit() {
    if (this.senha && this.valor && this.valor > 0) {
      this.mensagem = `Saque de R$ ${this.valor.toFixed(2)} realizado com sucesso para a conta ${this.senha}.`;
      this.senha = '';
      this.valor = null;
    } else {
      this.mensagem = 'Por favor, preencha todos os campos corretamente.';
    }
  }
}
