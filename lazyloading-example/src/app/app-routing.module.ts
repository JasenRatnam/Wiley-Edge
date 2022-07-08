import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = 
[
  { 
    path: 'stocks', 
    loadChildren: () => import('./stocks/stocks.module').then(m => m.StocksModule) 
  },
  { 
    path: 'bonds', 
    loadChildren: () => import('./bonds/bonds.module').then(m => m.BondsModule) 
  },
  //default route
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
