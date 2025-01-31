import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

interface Log {
  id: number;
  username: string;
  operation: string;
  date: string;
}

@Component({
  selector: 'app-admlogs',
  imports: [FormsModule, CommonModule],
  templateUrl: './admlogs.component.html',
  styleUrls: ['./admlogs.component.css']
})
export class AdmlogsComponent implements OnInit {
  logs: Log[] = [];
  searchTerm: string = '';
  private apiUrl = 'URL_DO_SEU_BACKEND';

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
    return this.logs.filter(log =>
      log.username.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
