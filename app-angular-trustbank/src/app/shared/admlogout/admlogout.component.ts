import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admlogout',
  imports: [],
  templateUrl: './admlogout.component.html',
  styleUrl: './admlogout.component.css'
})
export class AdmlogoutComponent {
  constructor(private router: Router){}

  logout(){
    localStorage.clear();
    sessionStorage.clear();

    //Redireciona para a página inicial ou de login
    this.router.navigate(['/']);
  }
  return(){
    this.router.navigate(['/adm/admstart'])
  }

}