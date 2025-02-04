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
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }).pipe(
      tap((response: any) => {
        this.storeUserData(response);
      })
    );
  }
  getidAdmin(): number {
    return Number(localStorage.getItem('idAdmin'));  
  }
  getnomeUsuario(): string | null {
    return localStorage.getItem('nomeUsuario');
  }  
  storeUserData(response: any): void {
    // Armazenando os dados no localStorage
    localStorage.setItem('idAdmin', response.idAdmin.toString());
    localStorage.setItem('nomeUsuario', response.nomeUsuario.toString());
  }

  getUserData(): any {
    return {
      idAdmin: localStorage.getItem('idAdmin'),
      nomeUsuario: localStorage.getItem('nomeUsuario')
    };
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