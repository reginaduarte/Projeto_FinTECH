import { CommonModule, CurrencyPipe, registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CardComponent } from "../card/card.component";
import { DollarComponent } from "../dollar/dollar.component";
import { SaldoService } from '../../services/saldo.service';
import { AuthService } from '../../services/auth.service';

registerLocaleData(localePt);

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css'],
  imports: [CommonModule, FormsModule, CardComponent, DollarComponent, CurrencyPipe]
})
export class StartComponent implements OnInit {
  saldo: number = 0;
  isSaldoVisible: boolean = false;  
  nomeUsuario: string | null = '';

  constructor(
    private saldoService: SaldoService,
    private authService: AuthService  
  ) { }

  ngOnInit(): void {
    // Pegando o idConta do localStorage
    const idConta = localStorage.getItem('idConta');
    if (idConta) {
      this.getSaldo(Number(idConta)); 
    } else {
      console.error('ID da conta não encontrado');
    }
  
    // Pegando o nome do usuário e extraindo o primeiro nome
    const nomeCompleto = this.authService.getNomeUsuario(); 
    if (nomeCompleto) {
      this.nomeUsuario = nomeCompleto.split(' ')[0]; // Pega apenas o primeiro nome
    }
  }

  getSaldo(idConta: number): void {
    this.saldoService.getSaldo(idConta).subscribe({
      next: (response) => {
        console.log('Saldo recebido:', response, 'Tipo:', typeof response);
        this.saldo = response;  
      },
      error: (error) => {
        console.error('Erro ao buscar o saldo', error);
      }
    });
  }

  toggleSaldoVisibility(): void {
    this.isSaldoVisible = !this.isSaldoVisible;
  }
}