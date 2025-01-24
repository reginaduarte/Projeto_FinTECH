import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-saldo',
  templateUrl: './saldo.component.html',
  styleUrls: ['./saldo.component.css'],
  imports: [CommonModule]
})
export class SaldoComponent {
  // Exemplo de saldo inicial
  saldoAtual: number = 5000;  
}
