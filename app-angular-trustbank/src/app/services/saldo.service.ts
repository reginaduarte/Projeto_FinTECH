import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SaldoService {
  private baseUrl = 'http://localhost:8080/users'; 

  constructor(private http: HttpClient) {}

  // Método para buscar o saldo por ID da conta
  getSaldo(idConta: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/${idConta}/saldo`);
  }
}