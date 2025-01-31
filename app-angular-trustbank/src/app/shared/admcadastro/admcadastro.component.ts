import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-admcadastro',
  templateUrl: './admcadastro.component.html',
  styleUrls: ['./admcadastro.component.css'],
  imports: [CommonModule, FormsModule],
  providers: [DatePipe]
})
export class AdmcadastroComponent {
  usuario = {
    nomeUsuario: '',
    cpfUsuario: '',
    emailUsuario: '',
    senhaUsuario: '',
    tipoUsuario: 2,
    dataCriacao: '',
    telefoneUsuario: ''
  };

  conta = {
    numeroConta: 0,
    numAgencia: 1221,
    tipoConta: 1,
    saldoConta: 0.0,
    dataAbertura: ''
  };

  usuarioParaEdicao: any = null;
  mensagemSucesso: string = '';  // Mensagem de sucesso
  modalVisivel: boolean = false;  // Controle de visibilidade do modal

  constructor(private http: HttpClient, private datePipe: DatePipe) {}

  cadastrarUsuario() {
    const urlUsuario = 'http://localhost:8080/users/novo';

    const dataFormatada = this.datePipe.transform(new Date(), 'dd/MM/yyyy HH:mm:ss');
    this.usuario.dataCriacao = dataFormatada || new Date().toISOString();

    console.log('Enviando usuário:', this.usuario);

    this.http.post(urlUsuario, this.usuario).subscribe(
      (response) => {
        console.log('Usuário cadastrado com sucesso:', response);
        this.mensagemSucesso = 'Usuário cadastrado com sucesso!';
        this.modalVisivel = true;  // Exibe o modal
        this.cadastrarConta(this.usuario.cpfUsuario);
      },
      (error) => {
        console.error('Erro ao cadastrar o usuário:', error);
      }
    );
  }

  cadastrarConta(cpf: string) {
    const urlConta = `http://localhost:8080/contas/nova/${cpf}`;

    const dataFormatada = this.datePipe.transform(new Date(), 'dd/MM/yyyy HH:mm:ss');
    this.conta.dataAbertura = dataFormatada || new Date().toISOString();

    console.log('Enviando conta:', this.conta);

    this.http.post(urlConta, this.conta).subscribe(
      (response) => {
        console.log('Conta cadastrada com sucesso:', response);
        this.mensagemSucesso = 'Conta cadastrada com sucesso!';
        this.modalVisivel = true;  // Exibe o modal
      },
      (error) => {
        console.error('Erro ao cadastrar a conta:', error);
      }
    );
  }

  fecharModal() {
    this.modalVisivel = false;  // Fecha o modal
  }
}
