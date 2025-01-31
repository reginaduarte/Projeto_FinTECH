import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-admstart',
  imports: [RouterLink],
  templateUrl: './admstart.component.html',
  styleUrl: './admstart.component.css'
})
export class AdmstartComponent {

  constructor(router : Router){}

}
