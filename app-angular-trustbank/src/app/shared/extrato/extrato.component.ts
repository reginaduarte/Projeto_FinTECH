import { Component } from '@angular/core';
import { ExtratoService } from '../../services/extrato.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css'],
  imports:[FormsModule, CommonModule]
})
export class ExtratoComponent {
  dataInicio: string = '';
  dataFim: string = '';
  transacoes: any[] = []; // Dados das transações
  idConta: number = 1; // ID fixo Mudar

  constructor(private extratoService: ExtratoService) {}

  filtrarExtrato(): void {
    console.log('Data Início:', this.dataInicio);
    console.log('Data Fim:', this.dataFim);
    console.log('ID Conta:', this.idConta);
  
    // Validação inicial
    if (!this.dataInicio || !this.dataFim) {
      alert('Por favor, preencha as datas de início e fim.');
      return;
    }
  
    // Chamando o serviço para buscar as transações
    this.extratoService.getExtrato(this.idConta).subscribe({
      next: (data) => {
        console.log('Dados recebidos:', data);
        this.transacoes = data.filter((transacao) => {
          const dataTransacao = this.parseDataTransacao(transacao.dataTransacao);
          const inicio = new Date(this.dataInicio);
          const fim = new Date(this.dataFim);
  
          // Ignorar hora e minutos na comparação de data
          dataTransacao.setHours(0, 0, 0, 0); 
          inicio.setHours(0, 0, 0, 0);
          fim.setHours(23, 59, 59, 999); 
  
          console.log('dataTransacao:', dataTransacao);
          console.log('inicio:', inicio);
          console.log('fim:', fim);
  
          // Comparar as datas, ignorando horário
          return dataTransacao >= inicio && dataTransacao <= fim;
        });
        console.log('Transações filtradas:', this.transacoes);
      },
      error: (err) => {
        console.error('Erro ao buscar o extrato:', err);
        alert('Erro ao buscar o extrato. Tente novamente mais tarde.');
      },
    });
  }
  
  // Função para converter a data da transação para um objeto Date
  private parseDataTransacao(data: string): Date {
    const partes = data.split(' '); // Dividir a data e hora
    const dataPartes = partes[0].split('/'); // Dividir a data no formato dd/MM/yyyy
    const horaPartes = partes[1].split(':'); // Dividir a hora no formato HH:mm:ss
  
    const ano = parseInt(dataPartes[2]);
    const mes = parseInt(dataPartes[1]) - 1; // Meses começam de 0 (janeiro = 0)
    const dia = parseInt(dataPartes[0]);
    const hora = parseInt(horaPartes[0]);
    const minuto = parseInt(horaPartes[1]);
    const segundo = parseInt(horaPartes[2]);
  
    // Retornar a data em formato Date
    return new Date(ano, mes, dia, hora, minuto, segundo);
  }
  
}
