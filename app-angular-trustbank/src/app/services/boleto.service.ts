// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable, tap } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class BoletoService {
//   private pagarBoletoUrl = 'http://localhost:8080/boleto/processar';  

//   constructor(private http: HttpClient) {}

//   pagarBoleto(codigoBarras: string, valor: number, senha: string): Observable<any> {
//     const body = {
//       idConta: localStorage.getItem('idConta'), // A conta vem do localStorage
//       codigoBoleto: codigoBarras,               // Ou você pode manter o nome do campo de acordo com seu DTO
//       descricaoTransacao: `Pagamento de R$ ${valor}`, // Para o campo de descrição, você pode adaptar conforme necessário
//     };

//     return this.http.post(this.pagarBoletoUrl, body, {
//       headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
//       responseType: 'json'
//     }).pipe(
//       tap((response: any) => {
//         console.log('Pagamento realizado com sucesso!', response);
//       })
//     );
//   }
// }

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoletoService {
  private pagarBoletoUrl = 'http://localhost:8080/boleto/processar';  
  private buscarBoletoUrl = 'http://localhost:8080/boleto/lista'; // URL fictícia para buscar as informações do boleto

  constructor(private http: HttpClient) {}

  // Método para buscar informações do boleto
  getBoletoInfo(codigoBarras: string): Observable<any> {
    return this.http.get<any>(`${this.buscarBoletoUrl}/${codigoBarras}`).pipe(
      tap((response) => {
        console.log('Informações do boleto:', response);
      })
    );
  }

  // Método para pagar o boleto 

  pagarBoleto(codigoBarras: string, valor: number, senha: string, descricao: string): Observable<any> {
    const body = {
      idConta: localStorage.getItem('idConta'), // A conta vem do localStorage
      codigoBoleto: codigoBarras,               // Ou você pode manter o nome do campo de acordo com seu DTO
      descricaoTransacao: descricao,            // Passando a descrição personalizada
    };

    return this.http.post(this.pagarBoletoUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'json'
    }).pipe(
      tap((response: any) => {
        console.log('Pagamento realizado com sucesso!', response);
      })
    );
  }
}
