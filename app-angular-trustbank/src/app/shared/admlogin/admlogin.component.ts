import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admlogin', // Certifique-se de que o seletor é este
  standalone: true, // Certifique-se de que está configurado como standalone
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './admlogin.component.html',
  styleUrls: ['./admlogin.component.css'],
})
export class AdmLoginComponent {
  @Output() close = new EventEmitter<void>();

  username = '';
  password = '';

  onSubmit() {
    console.log('Login do administrador realizado:', {
      username: this.username,
      password: this.password,
    });
    this.close.emit(); // Fecha o modal após o login
  }

  closeLogin() {
    this.close.emit(); // Emite o evento para fechar o modal
  }
}
