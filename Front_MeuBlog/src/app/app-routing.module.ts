import { Component, NgModule } from '@angular/core';// o erro esta aqui voce estava importando a biblioteca component e ela estava interferindo no component da linha 10
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { InicioComponent } from './inicio/inicio.component';

import { LoginComponent } from './login/login.component';
import { TemaComponent } from './tema/tema.component';

const routes: Routes = [

  {path:'', redirectTo:'entrar', pathMatch: 'full'},

  {path:'entrar', component: LoginComponent},
  {path:'cadastrar', component: CadastrarComponent},

  {path: 'inicio', component: InicioComponent},
  {path: 'tema', component: TemaComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
