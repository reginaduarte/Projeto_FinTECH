import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css'],
  imports: [FormsModule]
})
export class ExtratoComponent {
  // Propriedades para o filtro de data
  dataInicio: string = '';
  dataFim: string = '';

  // Lista de transações (exemplo sem dados reais)
  transacoes = [
    { data: '2025-01-20', valor: 1500, tipo: 'Débito', status: 'Concluído' },
    { data: '2025-01-18', valor: 2000, tipo: 'Crédito', status: 'Concluído' },
    { data: '2025-01-15', valor: 500, tipo: 'Débito', status: 'Pendente' },
    { data: '2025-01-12', valor: 300, tipo: 'Crédito', status: 'Concluído' },
    { data: '2025-01-10', valor: 1200, tipo: 'Débito', status: 'Concluído' },
  ];

  // Método para filtrar as transações com base no período
  filtrarExtrato() {
    // Validar as datas e filtrar as transações
    if (this.dataInicio && this.dataFim) {
      this.transacoes = this.transacoes.filter(transacao => {
        const dataTransacao = new Date(transacao.data);
        const dataInicio = new Date(this.dataInicio);
        const dataFim = new Date(this.dataFim);
        return dataTransacao >= dataInicio && dataTransacao <= dataFim;
      });
    }
  }
}
