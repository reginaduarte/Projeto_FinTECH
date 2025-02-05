// formulario.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Solicitacao {
  idSolicitacao: number;
  cpf: string;
  nome: string;
  email: string;
  telefone: string;
  dataSolicitacao: string;
}

@Injectable({ providedIn: 'root' })
export class FormularioService {
  private apiUrl = 'http://localhost:8080/form/lista';

  constructor(private http: HttpClient) {}

  getSolicitacoes(): Observable<Solicitacao[]> {
    return this.http.get<Solicitacao[]>(this.apiUrl);
  }
}