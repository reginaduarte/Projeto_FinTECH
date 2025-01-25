import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/users/login';

  constructor(private http: HttpClient) {}

  login(numeroConta: number, numAgencia: number, senhaUsuario: string): Observable<any> {
    const body = { numeroConta, numAgencia, senhaUsuario };
    return this.http.post(this.loginUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'text' // Adiciona responseType: 'text' para lidar com resposta de texto
    });
  }
}