import { Component, OnInit } from '@angular/core';
import { TransacaoService } from '../../services/transacao.service';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html',
  styleUrls: ['./deposito.component.css'],
  imports: [CommonModule, FormsModule]
})
export class DepositoComponent implements OnInit {
  numeroConta: string = '';
  numAgencia: string = '';
  valor: number = 0;
  senha: string = '';
  descricao: string = '';
  mensagem: string = '';

  constructor(private transacaoService: TransacaoService, private authService: AuthService) {}

  ngOnInit(): void {
    // Pegando os dados de conta e agência do localStorage
    this.numeroConta = this.authService.getNumeroConta().toString();
    this.numAgencia = this.authService.getNumAgencia().toString();
  }

  // Método para realizar o depósito
  onSubmit() {
    const depositoData = {
      idConta: this.authService.getIdConta(),  
      valor: this.valor,                       
      tipoTransacao: 2,                        
      descricaoTransacao: this.descricao      
    };

    // Chamando o serviço de transação para realizar o depósito
    this.transacaoService.depositar(depositoData).subscribe(
      (response) => {
        this.mensagem = `Depósito no valor de R$ ${this.valor.toFixed(2)} realizado com sucesso para a conta ${this.numeroConta}, agência ${this.numAgencia}!`;
        console.log('Resposta do servidor:', response);
        this.resetForm();
      },
      (error) => {
        this.mensagem = 'Erro ao realizar o depósito. Verifique os dados.';
        console.error('Erro:', error);
      }
    );
  }

  // Método para limpar o formulário
  resetForm() {
    this.valor = 0;
    this.senha = '';
    this.descricao = '';
  }
}
