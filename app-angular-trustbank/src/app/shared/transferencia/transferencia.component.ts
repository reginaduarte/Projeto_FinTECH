import { Component } from '@angular/core';
import { TransferenciaService } from '../../services/transferencia.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css'],
  imports:[CommonModule, FormsModule]
})
export class TransferenciaComponent {
  conta!: number; // Número da conta destino
  agencia!: number; // Número da agência destino
  valor!: number; // Valor a ser transferido
  senha!: string; // Senha para autenticação
  tipoTransferencia: string = 'ted'; // Tipo de transferência fixado como TED
  mensagem: string | null = null; // Mensagem de sucesso ou erro

  constructor(private transferenciaService: TransferenciaService) {}

  onSubmit(): void {
    // Objeto para a transferência baseado nos campos do formulário
    const transferencia = {
      idConta: 1, // ID fixo da conta de origem
      numeroAgenciaDestino: this.agencia, // Agência da conta destino
      numeroContaDestino: this.conta, // Conta destino
      valor: this.valor, // Valor a ser transferido
      tipoTransacao: 0,  
      descricaoTransacao: 'Transferência', // Descrição fixa
      temTarifa: this.tipoTransferencia === 'ted' ? 1 : 0, // Define a tarifa com base no tipo de transferência
    };

    // Chamada ao serviço para realizar a transferência
    this.transferenciaService.transferir(transferencia).subscribe({
      next: () => {
        this.mensagem = 'Transferência realizada com sucesso!';
      },
      error: (error) => {
        this.mensagem = 'Erro ao realizar a transferência. Verifique os dados e tente novamente.';
        console.error(error);
      },
    });
  }
}
