import { Component, OnInit } from '@angular/core';
import { TransferenciaService } from '../../services/transferencia.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css'],
  imports: [CommonModule, FormsModule]
})
export class TransferenciaComponent implements OnInit {
  conta!: number; 
  agencia!: number; 
  valor!: number; 
  senha!: string; 
  tipoTransferencia: string = 'ted'; 
  mensagem: string | null = null; 
  idConta!: number;  

  constructor(private transferenciaService: TransferenciaService) {}

  ngOnInit(): void {
    const idConta = localStorage.getItem('idConta');
    if (idConta) {
      this.idConta = Number(idConta);  
    } else {
      console.error('ID da conta não encontrado no localStorage');
    }
  }

  onSubmit(): void {
    if (!this.idConta) {
      this.mensagem = 'Conta não encontrada!';
      return;
    }

    const transferencia = {
      idConta: this.idConta,  
      numeroAgenciaDestino: this.agencia, 
      numeroContaDestino: this.conta, 
      valor: this.valor, 
      tipoTransacao: 0,  
      descricaoTransacao: 'TED', 
      temTarifa: this.tipoTransferencia === 'ted' ? 1 : 0, 
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
