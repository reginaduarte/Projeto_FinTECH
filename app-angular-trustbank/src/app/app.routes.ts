import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { TrustBankComponent } from './pages/trustbank/trustbank.component';
import { StartComponent } from './shared/start/start.component';
import { DepositoComponent } from './shared/deposito/deposito.component';
import { SaqueComponent } from './shared/saque/saque.component';
import { TransferenciaComponent } from './shared/transferencia/transferencia.component';
import { BoletoComponent } from './shared/boleto/boleto.component';
import { PixComponent } from './shared/pix/pix.component';
import { ExtratoComponent } from './shared/extrato/extrato.component';
import { SaldoComponent } from './shared/saldo/saldo.component';
import { ConfiguracaoComponent } from './shared/configuracao/configuracao.component';
import { LogoutComponent } from './shared/logout/logout.component';
import { AdmComponent } from './pages/adm/adm.component';
import { FormComponent } from './shared/form/form.component';
import { AdmlogoutComponent } from './shared/admlogout/admlogout.component';
import { MenuadmComponent } from './shared/menuadm/menuadm.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'adm', component: AdmComponent },
  {path: 'form', component: FormComponent },
  {
    path: 'trustbank',
    component: TrustBankComponent, 
    children: [
      { path: 'start', component: StartComponent },
      { path: 'deposito', component: DepositoComponent },
      { path: 'saque', component: SaqueComponent },
      { path: 'transferencia', component: TransferenciaComponent },
      { path: 'boleto', component: BoletoComponent },
      { path: 'pix', component: PixComponent },
      { path: 'extrato', component: ExtratoComponent },
      { path: 'saldo', component: SaldoComponent },
      { path: 'configuracao', component: ConfiguracaoComponent },
      { path: 'logout', component: LogoutComponent },
      { path: '', redirectTo: 'start', pathMatch: 'full' } 
    ]
  },
  { path: 'adm', component: AdmComponent,
    children: [
      // { path: 'admstart', component: AdmstartComponent},
      // { path: 'admcadastro', component: AdmcadastroComponent },
      // { path: 'visualizar', component: VisualizarComponent },
      // { path: 'admlogs', component: AdmlogsComponent },
      { path: 'admlogout', component: AdmlogoutComponent },
      { path: '', redirectTo: 'admstart', pathMatch: 'full' }
    ]
   },
];