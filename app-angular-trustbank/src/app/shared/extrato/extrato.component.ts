import { Component, OnInit } from '@angular/core';
import localePt from '@angular/common/locales/pt';
import { ExtratoService } from '../../services/extrato.service';
import { AuthService } from '../../services/auth.service'; 
import { FormsModule } from '@angular/forms';
import { CommonModule, CurrencyPipe, registerLocaleData } from '@angular/common';

registerLocaleData(localePt);

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css'],
  imports: [FormsModule, CommonModule, CurrencyPipe],
})
export class ExtratoComponent implements OnInit {
  dataInicio: string = '';
  dataFim: string = '';
  transacoes: any[] = []; 
  idConta: number = 0; 
  tipoOperacao: string = '';

  constructor(
    private extratoService: ExtratoService,
    private authService: AuthService 
  ) {}

  ngOnInit(): void {
    // Obtendo o ID da conta automaticamente do AuthService
    this.idConta = this.authService.getIdConta();
    console.log('ID da Conta:', this.idConta);
  }

  filtrarExtrato(): void {
    console.log('Data Início:', this.dataInicio);
    console.log('Data Fim:', this.dataFim);
    console.log('ID Conta:', this.idConta);
  
    // Validação inicial
    if (!this.dataInicio || !this.dataFim) {
      alert('Por favor, preencha as datas de início e fim.');
      return;
    }
    const dataFimUm = this.addOneDayToDate(this.dataFim);
  
    // Chamando o serviço para buscar as transações
    this.extratoService.getExtrato(this.idConta).subscribe({
      next: (data) => {
        console.log('Dados recebidos:', data);
        this.transacoes = data.filter((transacao) => {
          const dataTransacao = this.parseDataTransacao(transacao.dataTransacao);
          const inicio = new Date(this.dataInicio);
          const fim = new Date(dataFimUm); 
  
          // Normalizando as datas (removendo o fuso horário da comparação)
          inicio.setHours(0, 0, 0, 0);
          fim.setHours(23, 59, 59, 999);
          dataTransacao.setHours(0, 0, 0, 0);  
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
  
  private addOneDayToDate(date: string): string {
    const partes = date.split('-');
    const data = new Date(Number(partes[0]), Number(partes[1]) - 1, Number(partes[2]));
    data.setDate(data.getDate() + 1); 
    const novaData = data.toISOString().split('T')[0];
    return novaData;
  }
  

  // Função para converter a data da transação em string para Date
  private parseDataTransacao(data: string): Date {
    // Divide a data e hora
    const partes = data.split(' ');
    const dataPartes = partes[0].split('/');
    const horaPartes = partes[1].split(':');
    // Extraindo as partes da data e hora
    const dia = parseInt(dataPartes[0]);
    const mes = parseInt(dataPartes[1]) - 1;  
    const ano = parseInt(dataPartes[2]);
    const hora = parseInt(horaPartes[0]);
    const minuto = parseInt(horaPartes[1]);
    const segundo = horaPartes[2] ? parseInt(horaPartes[2]) : 0; 
  
    // Criando a data com os dados extraídos
    return new Date(ano, mes, dia, hora, minuto, segundo);
  }
}
