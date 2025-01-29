import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pix',
  templateUrl: './pix.component.html',
  styleUrls: ['./pix.component.css'],
  imports: [FormsModule, CommonModule]
})
export class PixComponent {
  tipoChave: string = '';
  chavePix: string = '';
  data: string = '';
  valor: number = 0;
  descricao: string = ''; 
  senha: string = '';
  mensagem: string | null = null;

  // Método chamado quando o formulário é submetido
  onSubmit() {
    if (this.tipoChave && this.chavePix && this.data && this.valor && this.senha) {
      // Simula o envio dos dados e retorna uma mensagem de sucesso
      this.mensagem = 'Pix processado com sucesso!';
    } else {
      // Mensagem de erro se algum campo não for preenchido
      this.mensagem = 'Por favor, preencha todos os campos.';
    }

    // Limpa os campos após a submissão 
    this.tipoChave = '';
    this.chavePix = '';
    this.data = '';
    this.valor = 0;
    this.descricao = '';
    this.senha = '';
  }
}
