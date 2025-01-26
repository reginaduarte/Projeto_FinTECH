import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { SaldoService } from '../../services/saldo.service';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-saldo',
  templateUrl: './saldo.component.html',
  styleUrls: ['./saldo.component.css'],
  imports: [CurrencyPipe]
})
export class SaldoComponent implements OnInit {

  saldo: number = 0;  // Inicializa o saldo como 0

  constructor(
    private saldoService: SaldoService,
    private authService: AuthService  // Injetando o AuthService
  ) { }

  ngOnInit(): void {
    // Pegando o idConta do localStorage
    const idConta = localStorage.getItem('idConta');
    if (idConta) {
      this.getSaldo(Number(idConta)); 
    } else {
      console.error('ID da conta não encontrado');
    }
  }

  getSaldo(idConta: number): void {
    this.saldoService.getSaldo(idConta).subscribe({
      next: (response) => {
        this.saldo = response;  
      },
      error: (error) => {
        console.error('Erro ao buscar o saldo', error);
      }
    });
  }
}