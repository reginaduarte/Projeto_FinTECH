import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Log {
  idLog: number;
  codAdmin: string;
  idCliente: number;
  tipoAlteracao: string;
  dataHoraAlteracao: string; 
  campoAlterado: string | null;
}

@Component({
  selector: 'app-admlogs',
  templateUrl: './admlogs.component.html',
  styleUrls: ['./admlogs.component.css'],
  imports: [CommonModule, FormsModule]
})
export class AdmlogsComponent implements OnInit {
  logs: Log[] = [];
  searchTerm: string = '';
  private apiUrl = 'http://localhost:8080/logs/lista';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getLogs();
  }

  getLogs(): void {
    this.http.get<Log[]>(this.apiUrl).subscribe((logs: Log[]) => {
      this.logs = logs;
    });
  }

  filterLogs(): Log[] {
    const lowerCaseSearchTerm = this.searchTerm.toLowerCase();
    return this.logs.filter(log =>
      log.idCliente.toString().includes(lowerCaseSearchTerm)
    );
  }
}
