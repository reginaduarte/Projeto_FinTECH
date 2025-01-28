import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TransacaoService {
  private apiUrl = 'http://localhost:8080/contas';  

  constructor(private http: HttpClient) {}

  // Método para realizar o saque
  realizarSaque(saque: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/realizarTransacao`, saque, { headers });
  }

  // Método para realizar o depósito
  depositar(deposito: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/depositar`, deposito, { headers });
  }}
