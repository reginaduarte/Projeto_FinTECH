import { Component } from '@angular/core';

@Component({
  selector: 'app-adm',
  standalone: true,
  templateUrl: './adm.component.html',
  styleUrls: ['./adm.component.css'],
})
export class AdmComponent {
  //Implementação do CRUD
  onCreate() {
    alert('Criar ação clicada!');
  }

  onRead() {
    alert('Ler ação clicada!');
  }

  onUpdate() {
    alert('Atualizar ação clicada!');
  }

  onDelete() {
    alert('Deletar ação clicada!');
  }
}
