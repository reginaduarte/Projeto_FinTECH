import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admlogin',
  standalone: true,
  imports: [CommonModule,RouterLink, FormsModule],
  templateUrl: './admlogin.component.html',
  styleUrls: ['./admlogin.component.css']
})
export class AdmLoginComponent {
  @Output() close = new EventEmitter<void>();

  username = '';
  password = '';

  onSubmit() {
    console.log('Login realizado:', { username: this.username, password: this.password });
    this.close.emit(); // Fecha o modal após o login
  }

  closeLogin() {
    this.close.emit(); // Emite um evento para fechar o modal
  }
}