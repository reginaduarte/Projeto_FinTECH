import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SolicitacaoService {
  private apiUrl = 'http://localhost:8080/form/solicitar';

  constructor(private http: HttpClient) {}

  solicitarCadastro(dados: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, dados);
  }
}