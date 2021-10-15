import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConcessoesPPPsModule } from './cadastro/concessoes-ppps/concessoes-ppps.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ConcessoesPPPsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
