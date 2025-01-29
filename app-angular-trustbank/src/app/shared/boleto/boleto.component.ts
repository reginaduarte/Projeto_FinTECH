import { Component } from '@angular/core';
import { BoletoService } from '../../services/boleto.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-boleto',
  templateUrl: './boleto.component.html',
  styleUrls: ['./boleto.component.css'],
  imports: [CommonModule, FormsModule]
})
export class BoletoComponent {
  codigoBarras: string = '';
  vencimento: string = '';
  valor: number = 0;
  senha: string = '';
  descricao: string = '';  
  mensagem: string = '';

  constructor(private boletoService: BoletoService) {}

  // Função para buscar as informações do boleto
  buscarInformacoesBoleto(): void {
    if (this.codigoBarras) {
      this.boletoService.getBoletoInfo(this.codigoBarras).subscribe({
        next: (data) => {
          if (data) {
            this.vencimento = this.formatDate(data.dataVencimento);  
            this.valor = data.valorBoleto;  
          } else {
            this.mensagem = 'Informações não encontradas para o código de barras informado.';
          }
        },
        error: (err) => {
          console.error('Erro ao buscar informações do boleto:', err);
          this.mensagem = 'Erro ao buscar informações do boleto.';
        }
      });
    }
  }

  // Função para formatar a data
  formatDate(dateString: string): string {
    if (!dateString) {
      return '';  
    }

    const partes = dateString.split(' ')[0].split('/');

    if (partes.length !== 3) {
      console.error('Data com formato inesperado:', dateString);
      return '';
    }

    const dia = partes[0];
    const mes = partes[1];
    const ano = partes[2];

    return `${ano}-${mes}-${dia}`;
  }

  onSubmit(): void {
    if (this.codigoBarras && this.valor && this.senha && this.descricao) {
      this.boletoService.pagarBoleto(this.codigoBarras, this.valor, this.senha, this.descricao).subscribe(
        (response) => {
          this.mensagem = 'Pagamento realizado com sucesso!';
        },
        (error) => {
          this.mensagem = 'Erro ao pagar boleto: ' + error.message;
        }
      );
    } else {
      this.mensagem = 'Por favor, preencha todos os campos.';
    }
  }
}
