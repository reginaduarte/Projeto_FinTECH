import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,RouterLink, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Output() close = new EventEmitter<void>();

  agencia = '';
  conta = '';

  onSubmit() {
    console.log('Login realizado:', { agencia: this.agencia, conta: this.conta });
    this.close.emit(); // Fecha o modal após o login
  }

  closeLogin() {
    this.close.emit(); // Emite um evento para fechar o modal
  }
}
