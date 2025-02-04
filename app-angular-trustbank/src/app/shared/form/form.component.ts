import { CommonModule } from '@angular/common';
import { Component} from '@angular/core';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  imports: [CommonModule, FormsModule]
})
export class FormComponent {
  
  allowNumbersOnly(event: any): void {
    event.target.value = event.target.value.replace(/[^0-9]/g, '');// Permite apenas números
  }

  allowLettersAndNumbers(event: any): void {
    event.target.value = event.target.value.replace(/[^a-zA-Z0-9]/g, '');// Permite apenas letras e números
  }
 // Formatar CPF com pontos e traço
 formatCPF(event: any): void {
  let cpf = event.target.value.replace(/\D/g, ''); // Remove tudo que não é dígito

  // Limitar o CPF a 11 dígitos
  if (cpf.length > 11) {
    cpf = cpf.slice(0, 11);
  }

  // Formatar CPF
  if (cpf.length <= 11) {
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
  }

  event.target.value = cpf;
}

// Formatar Telefone com parênteses e traço
formatTelefone(event: any): void {
  let telefone = event.target.value.replace(/\D/g, ''); // Remove tudo que não é dígito

  if (telefone.length > 2) {
    telefone = '(' + telefone.slice(0, 2) + ') ' + telefone.slice(2);
  }
  if (telefone.length > 10) {
    telefone = telefone.slice(0, 10) + '-' + telefone.slice(10, 14);
  }

  event.target.value = telefone;
}

// Formatar CEP com traço
formatCEP(event: any): void {
  let cep = event.target.value.replace(/\D/g, ''); // Remove tudo que não é dígito

  if (cep.length > 5) {
    cep = cep.slice(0, 5) + '-' + cep.slice(5, 9);
  }

  event.target.value = cep;
}
 
}