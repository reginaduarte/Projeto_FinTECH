import { Component, OnInit } from '@angular/core';
import { TransacaoService } from '../../services/transacao.service';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html',
  styleUrls: ['./saque.component.css'],
  imports: [FormsModule, CommonModule]
})
export class SaqueComponent implements OnInit {
  numeroConta: string = '';
  numAgencia: string = '';
  valor: number = 0;
  senha: string = '';
  descricao: string = '';
  mensagem: string = '';
  idConta: number | null = null; // ID da conta obtido do localStorage

  constructor(
    private transacaoService: TransacaoService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.numeroConta = this.authService.getNumeroConta().toString();
    this.numAgencia = this.authService.getNumAgencia().toString();
    // Obtendo o ID da conta do localStorage via AuthService
    this.idConta = this.authService.getIdConta();
    if (!this.idConta) {
      console.error('ID da conta não encontrado no localStorage.');
    }
  }

  onSubmit() {
    if (!this.idConta) {
      this.mensagem = 'ID da conta não encontrado. Não é possível realizar o saque.';
      return;
    }

    const saqueData = {
      idConta: this.idConta, 
      valor: this.valor, 
      tipoTransacao: 1, 
      descricaoTransacao: this.descricao, 
    };

    this.transacaoService.realizarSaque(saqueData).subscribe(
      (response) => {
        this.mensagem = 'Saque realizado com sucesso!';
        console.log('Resposta do servidor:', response);

        // Limpa os campos do formulário
        this.valor = 0;
        this.senha = '';
        this.descricao = '';
      },
      (error) => {
        this.mensagem = 'Erro ao realizar o saque. Verifique os dados.';
        console.error('Erro:', error);
      }
    );
  }
}