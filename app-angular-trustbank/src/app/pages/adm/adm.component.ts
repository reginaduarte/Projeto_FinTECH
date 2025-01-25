import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adm',
  standalone: true,
  templateUrl: './adm.component.html',
  styleUrls: ['./adm.component.css'],
  imports: [],
})
export class AdmComponent {
  constructor(private router: Router) {}
  //Implementação do CRUD
  onCreate() {
    this.router.navigate(['/form']);
  }

  onRead() {}

  onUpdate() {}

  onDelete() {}
}
