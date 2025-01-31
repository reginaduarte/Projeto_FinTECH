import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AtualizarService {
  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  atualizarDados(usuario: any): Observable<string> {
    const url = `${this.apiUrl}/atualizar`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(url, usuario, {
      headers: headers,
      responseType: 'text',
    });
  }
}