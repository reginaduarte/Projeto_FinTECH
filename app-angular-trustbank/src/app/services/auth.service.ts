import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/users/login';
  private idConta: number = 0;

  constructor(private http: HttpClient) {}

  login(numeroConta: number, numAgencia: number, senhaUsuario: string): Observable<any> {
    const body = { numeroConta, numAgencia, senhaUsuario };
    return this.http.post(this.loginUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'json'
    }).pipe(
      tap((response: any) => {
        // Armazenando os dados do usuário após o login
        this.storeUserData(response);  // Chama o método para armazenar os dados
      })
    );
  }

  storeUserData(response: any): void {
    // Armazenando os dados no localStorage
    localStorage.setItem('idConta', response.idConta.toString());
    localStorage.setItem('numeroConta', response.numeroConta.toString());  // Adicionando numeroConta
    localStorage.setItem('numAgencia', response.numAgencia.toString());      // Adicionando numAgencia
    localStorage.setItem('nomeUsuario', response.nomeUsuario);
    localStorage.setItem('cpfUsuario', response.cpfUsuario);
    localStorage.setItem('telefoneUsuario', response.telefoneUsuario);
    localStorage.setItem('emailUsuario', response.emailUsuario);
    localStorage.setItem('tipoConta', response.tipoConta);
    localStorage.setItem('saldoConta', response.saldoConta.toString());
    localStorage.setItem('dataAbertura', response.dataAbertura);
  }

  getUserData(): any {
    return {
      idConta: localStorage.getItem('idConta'),
      numeroConta: localStorage.getItem('numeroConta'),  // Recuperando numeroConta
      numAgencia: localStorage.getItem('numAgencia'),    // Recuperando numAgencia
      nomeUsuario: localStorage.getItem('nomeUsuario'),
      cpfUsuario: localStorage.getItem('cpfUsuario'),
      telefoneUsuario: localStorage.getItem('telefoneUsuario'),
      emailUsuario: localStorage.getItem('emailUsuario'),
      tipoConta: localStorage.getItem('tipoConta'),
      saldoConta: localStorage.getItem('saldoConta'),
      dataAbertura: localStorage.getItem('dataAbertura')
    };
  }

  getIdConta(): number {
    return Number(localStorage.getItem('idConta'));  
  }

  getNumeroConta(): number {
    return Number(localStorage.getItem('numeroConta'));  // Método para pegar o numeroConta
  }

  getNumAgencia(): number {
    return Number(localStorage.getItem('numAgencia'));    // Método para pegar o numAgencia
  }
}