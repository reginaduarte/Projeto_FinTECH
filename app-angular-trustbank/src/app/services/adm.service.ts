import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdmService {
  private loginUrl = 'http://localhost:8080/admin/login';

  constructor(private http: HttpClient) {}

  login(emailUsuario: string, senhaUsuario: string): Observable<any> {
    const body = { emailUsuario, senhaUsuario };
    return this.http.post(this.loginUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'text' // Adiciona responseType: 'text' para lidar com resposta de texto
    });
  }
}