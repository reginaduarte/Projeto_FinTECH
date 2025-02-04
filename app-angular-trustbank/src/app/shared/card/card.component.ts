import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  nomeCliente: string = '';
  numeroConta: string = '';
  agencia: string = '';
  tipoConta: string = '';

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUserData();
  }

  loadUserData(): void {
    const userData = this.authService.getUserData();
    this.nomeCliente = userData.nomeUsuario ?? '';  
    this.numeroConta = userData.numeroConta ?? '';
    this.agencia = userData.numAgencia ?? '';
    this.tipoConta = userData.tipoConta ?? '';
  }
}
