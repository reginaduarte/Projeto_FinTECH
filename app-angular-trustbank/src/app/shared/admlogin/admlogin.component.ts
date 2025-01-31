import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AdmService } from '../../services/adm.service';

@Component({
  selector: 'app-admlogin',
  templateUrl: './admlogin.component.html',
  styleUrls: ['./admlogin.component.css'],
  imports: [FormsModule, CommonModule]
})
export class AdmLoginComponent {
  @Output() close = new EventEmitter<void>();

  emailUsuario!: string;
  senhaUsuario!: string;
  mensagemErro!: string;

  constructor(private admService : AdmService, private router: Router) { }

  // Função chamada quando o usuário tenta fazer login
  login(): void {
    this.admService.login(this.emailUsuario, this.senhaUsuario).subscribe({
      next: (response) => {
        console.log('Login bem-sucedido!', response);
        this.router.navigate(['/adm/admstart']);  
      },
      error: (err) => {
        console.error('Erro de login', err);
        if (err.status === 401) {
          this.mensagemErro = 'Credenciais inválidas!';
        } else {
          this.mensagemErro = 'Ocorreu um erro ao tentar fazer login. Tente novamente mais tarde.';
        }
      }
    });
  }
  closeLogin() {
    this.close.emit(); 
  }
}