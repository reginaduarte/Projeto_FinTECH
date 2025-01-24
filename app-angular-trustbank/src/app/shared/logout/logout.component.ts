import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  standalone: true,
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
})
export class LogoutComponent {
  constructor(private router: Router) {}

  logout() {
    localStorage.clear(); 
    sessionStorage.clear(); 

    // Redireciona para a página inicial ou de login
    this.router.navigate(['/']);
  }
  return() {
    this.router.navigate(['/trustbank/start'])
  }
}
