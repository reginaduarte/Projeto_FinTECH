import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-usuarioedicao',
  templateUrl: './usuarioedicao.component.html',
  styleUrls: ['./usuarioedicao.component.css'],
  imports: [FormsModule, CommonModule]
})
export class UsuarioEdicaoComponent {
  @Input() usuarioParaEdicao: any;
  @Output() cancelar = new EventEmitter<void>();
  @Output() salvar = new EventEmitter<any>();

  cancelarEdicao(): void {
    this.cancelar.emit(); 
  }

  salvarEdicao(): void {
    this.salvar.emit(this.usuarioParaEdicao); 
  }
}
