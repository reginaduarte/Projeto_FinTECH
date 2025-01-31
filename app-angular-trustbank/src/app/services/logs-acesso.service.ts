// logs-acesso.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogsAcessoService {
  private apiUrl = 'http://localhost:4200/adm/admlogs';

  constructor(private http: HttpClient) {}

  getLogs(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
