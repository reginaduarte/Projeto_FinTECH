import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class DollarService {
  private apiUrl = 'https://economia.awesomeapi.com.br/json/last/USD-BRL';

  constructor() { }

  async getDollarRate() {
    try {
      const response = await axios.get(this.apiUrl);
      return response.data.USDBRL;
    } catch (error) {
      console.error('Erro ao buscar a cotação do dólar:', error);
      throw error;
    }
  }
}