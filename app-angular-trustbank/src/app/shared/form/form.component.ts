import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SolicitacaoService } from '../../services/solicitacao.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  standalone: true,
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  imports: [CommonModule, FormsModule, HttpClientModule]
})
export class FormComponent {
  formData: any = {
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    cep: '',
    logradouro: '',
    numero: '',
    bairro: '',
    cidade: '',
    estado: '',
    senha: '',
    status: 1,
    dataSolicitacao: ''
  };

  mensagem: string | null = null;

  constructor(private router: Router, private solicitacaoService: SolicitacaoService) {}

  private formatarData(): string {
    const agora = new Date();
    const dia = String(agora.getDate()).padStart(2, '0');
    const mes = String(agora.getMonth() + 1).padStart(2, '0');
    const ano = agora.getFullYear();
    const horas = String(agora.getHours()).padStart(2, '0');
    const minutos = String(agora.getMinutes()).padStart(2, '0');
    const segundos = String(agora.getSeconds()).padStart(2, '0');

    return `${dia}/${mes}/${ano} ${horas}:${minutos}:${segundos}`;
  }

  enviarFormulario() {
    this.formData.dataSolicitacao = this.formatarData();

    this.solicitacaoService.solicitarCadastro(this.formData).subscribe({
      next: (response) => {
        this.mensagem = 'Solicitação de cadastro realizada com sucesso!';
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 500000);
      },
      error: (error) => {
        this.mensagem = 'Erro ao cadastrar. Tente novamente.';
      }
    });
  }
    navegarParaHome() {
    this.router.navigate(['/']);
  }
}
