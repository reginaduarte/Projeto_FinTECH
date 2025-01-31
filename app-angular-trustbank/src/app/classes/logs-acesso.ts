export class LogsAcesso {
    date: string;
    time: string;
    clientId: number;
    accessInfo: string;
  
    constructor(date: string, time: string, clientId: number, accessInfo: string) {
      this.date = date;
      this.time = time;
      this.clientId = clientId;
      this.accessInfo = accessInfo;
    }
  }
  