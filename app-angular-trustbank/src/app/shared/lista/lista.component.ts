import { Component, OnInit } from '@angular/core';
import { FormularioService } from '../../services/formulario.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'; 

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css'],
  imports: [CommonModule]
})
export class ListaComponent implements OnInit {
  solicitacoes: any[] = [];
  mensagem: string | null = null; 

  constructor(
    private router: Router, 
    private formularioService: FormularioService,
    private http: HttpClient 
  ) {}

  ngOnInit() {
    this.formularioService.getSolicitacoes().subscribe((data) => {
      this.solicitacoes = data;
    });
  }

  aprovar(solicitacao: any) {
    const cpf = solicitacao.cpf;
    const url = `http://localhost:8080/users/new/${cpf}`;

    // Realiza a requisição HTTP para aprovar a solicitação
    this.http.post(url, {}).subscribe(
      (response) => {
        console.log('Solicitação aprovada:', response);
        this.mensagem = 'Solicitação aprovada com sucesso!'; 
      },
      (error) => {
        console.error('Erro ao aprovar solicitação:', error);
        this.mensagem = 'Erro ao aprovar a solicitação. Tente novamente mais tarde.'; 
      }
    );
  }

  recusar(solicitacao: any) {
    const cpf = solicitacao.cpf;
    const url = `http://localhost:8080/users/reprovar/${cpf}`;
  
    this.http.delete(url, { responseType: 'text' }).subscribe(
      (response) => {
        console.log('Solicitação reprovada:', response);
        this.mensagem = 'Solicitação reprovada!'; 
  
        // Atualizar a lista removendo a solicitação reprovada
        this.solicitacoes = this.solicitacoes.filter(s => s.cpf !== cpf);
      },
      (error) => {
        console.error('Erro ao reprovar solicitação:', error);
        this.mensagem = 'Erro ao reprovar a solicitação. Tente novamente mais tarde.';
      }
    );
  }
  
  

  adicionarSolicitacao() {
    this.router.navigate(['/adm/admcadastro']); 
  }

  fecharModal() {
    this.mensagem = null; 
  }
}