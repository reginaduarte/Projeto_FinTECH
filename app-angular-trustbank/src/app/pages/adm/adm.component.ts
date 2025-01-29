import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuComponent } from "../../shared/menu/menu.component";
import { MenuadmComponent } from "../../shared/menuadm/menuadm.component";

@Component({
  selector: 'app-adm',
  standalone: true,
  templateUrl: './adm.component.html',
  styleUrls: ['./adm.component.css'],
  imports: [MenuadmComponent],
})
export class AdmComponent {
  constructor(private router: Router) {}
}
