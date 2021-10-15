import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConcessoesPPPsFormComponent } from './cadastro/concessoes-ppps/concessoes-ppps-form/concessoes-ppps-form.component';

const routes: Routes = [
  {
    path: 'concessoes-ppps',
    component: ConcessoesPPPsFormComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
