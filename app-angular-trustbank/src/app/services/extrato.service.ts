import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ExtratoService {
  private apiUrl = 'http://localhost:8080/contas/extrato';

  constructor(private http: HttpClient) {}

  getExtrato(idConta: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${idConta}`);
  }
}
