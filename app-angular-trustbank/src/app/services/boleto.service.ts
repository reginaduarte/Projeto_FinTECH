import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoletoService {
  private pagarBoletoUrl = 'http://localhost:8080/boleto/processar';  
  private buscarBoletoUrl = 'http://localhost:8080/boleto/lista'; 

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
      idConta: localStorage.getItem('idConta'), 
      codigoBoleto: codigoBarras,               
      descricaoTransacao: descricao,            
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
