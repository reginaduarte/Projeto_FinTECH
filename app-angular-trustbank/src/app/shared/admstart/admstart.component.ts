import { Component, OnInit } from '@angular/core';
import { AdmService } from '../../services/adm.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-admstart',
  templateUrl: './admstart.component.html',
  styleUrls: ['./admstart.component.css'],
  imports: [RouterLink]
})
export class AdmstartComponent implements OnInit {
  nomeAdmin: string | null = '';

  constructor(private router : Router,private admService: AdmService) {}

  ngOnInit() {
    const nomeCompleto = this.admService.getnomeUsuario()
    if (nomeCompleto) {
      this.nomeAdmin = nomeCompleto.split(' ')[0]; // Pega apenas o primeiro nome
    }
  }
}
