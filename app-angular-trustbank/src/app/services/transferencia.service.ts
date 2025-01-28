import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TransferenciaService {
  private apiUrl = 'http://localhost:8080/contas/transferir';

  constructor(private http: HttpClient) {}

  transferir(transferencia: any): Observable<any> {
    return this.http.post(this.apiUrl, transferencia);
  }
}