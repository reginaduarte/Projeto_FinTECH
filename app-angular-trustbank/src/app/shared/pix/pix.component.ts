import { Component } from '@angular/core';
import { PixService } from '../../services/pix.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

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
  mensagem: string = '';

  constructor(private pixService: PixService) {}

  onSubmit() {
    const idContaOrigem = localStorage.getItem('idConta'); // Pega do localStorage
    if (!idContaOrigem) {
      this.mensagem = 'Erro: Conta de origem não encontrada.';
      return;
    }
  
    const valor = this.valor;
    const chavePix = this.chavePix;
    const descricaoTransacao = this.descricao;
  
    this.pixService.processarPix(Number(idContaOrigem), valor, chavePix, descricaoTransacao).subscribe({
      next: (response) => {
        this.mensagem = 'Pix processado com sucesso!';
        console.log(response);
      },
      error: (err) => {
        this.mensagem = 'Erro ao processar o Pix: ' + err.error;
        console.error(err);
      }
    });
  }  
}