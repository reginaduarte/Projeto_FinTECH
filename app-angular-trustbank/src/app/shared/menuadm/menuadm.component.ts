import { Component } from '@angular/core';
import {Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-menuadm',
  imports: [RouterModule],
  templateUrl: './menuadm.component.html',
  styleUrl: './menuadm.component.css'
})
export class MenuadmComponent {

  constructor(router : Router){}
}
