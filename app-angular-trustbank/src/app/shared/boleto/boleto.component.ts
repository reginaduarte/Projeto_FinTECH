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
  mensagem: string = '';

  constructor(private boletoService: BoletoService) {}

  onSubmit(): void {
    if (this.codigoBarras && this.valor && this.senha) {
      this.boletoService.pagarBoleto(this.codigoBarras, this.valor, this.senha).subscribe(
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
