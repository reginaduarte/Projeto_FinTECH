import { Component, EventEmitter, Output } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule, CommonModule]
})
export class LoginComponent {
  @Output() close = new EventEmitter<void>();

  numeroConta!: number;
  numAgencia!: number;
  senhaUsuario!: string;
  mensagemErro!: string;

  constructor(private authService: AuthService, private router: Router) { }

  // Função chamada quando o usuário tenta fazer login
  login(): void {
    this.authService.login(this.numeroConta, this.numAgencia, this.senhaUsuario).subscribe({
      next: (response) => {
        console.log('Login bem-sucedido!', response);
        this.router.navigate(['/trustbank']);  
      },
      error: (err) => {
        console.error('Erro de login', err);
        if (err.status === 401) {
          this.mensagemErro = 'Credenciais inválidas!';  // Exibe uma mensagem de erro
        } else {
          this.mensagemErro = 'Ocorreu um erro ao tentar fazer login. Tente novamente mais tarde.';
        }
      }
    });
  }
  closeLogin() {
    this.close.emit(); // Emite o evento para fechar o modal
  }
}