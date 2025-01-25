import { Component, Input, NgModule } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { CommonModule } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-visualizar',
  templateUrl: './visualizar.component.html',
  styleUrls: ['./visualizar.component.css'],
  imports: [FormsModule, CommonModule]
})
export class VisualizarComponent {
  @Input() usuarios: any[] = [];
  @Input() editarUsuario!: (index: number) => void;
  @Input() deletarUsuario!: (index: number) => void;
}

