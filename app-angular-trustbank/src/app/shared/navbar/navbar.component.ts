import { Component } from '@angular/core';
import { LoginComponent } from "../login/login.component";
import { AdmLoginComponent } from "../admlogin/admlogin.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, LoginComponent, AdmLoginComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  showClientLogin = false;
  showManagerLogin = false;

  openClientLogin() {
    this.showClientLogin = true;
    this.showManagerLogin = false;
  }

  openManagerLogin() {
    this.showManagerLogin = true;
    this.showClientLogin = false;
  }

  closeLogin() {
    this.showClientLogin = false;
    this.showManagerLogin = false;
  }
}