import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdmService {
  private loginUrl = 'http://localhost:8080/admin/login';
  private listarClientesUrl = 'http://localhost:8080/users/lista'; 

  constructor(private http: HttpClient) {}

  login(emailUsuario: string, senhaUsuario: string): Observable<any> {
    const body = { emailUsuario, senhaUsuario };
    return this.http.post(this.loginUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'text' 
    });
  }

  listarClientes(): Observable<any> {
    return this.http.get<any>(this.listarClientesUrl, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  editarUsuario(usuario: any): Observable<any> {
    const updateUrl = `http://localhost:8080/users/novo`;
    return this.http.put(updateUrl, usuario, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  deletarUsuario(cpfUsuario: string): Observable<string> {
    return this.http.delete<string>(`http://localhost:8080/users/deletar/${cpfUsuario}`, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'text' as 'json' 
    }).pipe(
      tap((response) => {
        console.log('Resposta após exclusão:', response);
      })
    );
  }  
}