import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PixService {
  private apiUrl = 'http://localhost:8080/pix/processar';

  constructor(private http: HttpClient) {}

  processarPix(idContaOrigem: number, valor: number, chavePix: string, descricaoTransacao: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, {
      idContaOrigem,
      valor,
      chavePix,
      descricaoTransacao
    });
  }
}