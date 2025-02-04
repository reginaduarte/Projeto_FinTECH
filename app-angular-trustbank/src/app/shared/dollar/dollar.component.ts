import { Component, OnInit } from '@angular/core';
import localePt from '@angular/common/locales/pt';
import { HttpClient } from '@angular/common/http';
import { CommonModule, CurrencyPipe, registerLocaleData } from '@angular/common';
import { FormsModule } from '@angular/forms';

registerLocaleData(localePt);

@Component({
  selector: 'app-dollar',
  templateUrl: './dollar.component.html',
  styleUrls: ['./dollar.component.css'],
  imports: [CommonModule, FormsModule, CurrencyPipe]
})
export class DollarComponent implements OnInit {
  dollarRate: any;
  lastUpdated: Date = new Date();
  usdValue: number = 1; 
  brlValue: number = 0; 

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getDollarRate();
  }

  getDollarRate() {
    this.http.get<any>('https://economia.awesomeapi.com.br/json/last/USD-BRL')
      .subscribe(response => {
        if (response && response.USDBRL) {
          this.dollarRate = response.USDBRL;
          this.lastUpdated = new Date();
          this.convertToBRL(); 
        }
      });
  }

  convertToBRL() {
    if (this.dollarRate) {
      this.brlValue = this.usdValue * parseFloat(this.dollarRate.ask);
    }
  }
}