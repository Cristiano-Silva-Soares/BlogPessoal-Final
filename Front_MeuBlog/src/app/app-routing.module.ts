import { Component, NgModule } from '@angular/core';// o erro esta aqui voce estava importando a biblioteca component e ela estava interferindo no component da linha 10
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { PostagemDeleteComponent } from './delete/postagem-delete/postagem-delete.component';
import { TemaDeleteComponent } from './delete/tema-delete/tema-delete.component';
import { PostagemEditComponent } from './edit/postagem-edit/postagem-edit.component';
import { TemaEditComponent } from './edit/tema-edit/tema-edit.component';
import { UserEditComponent } from './edit/user-edit/user-edit.component';
import { InicioComponent } from './inicio/inicio.component';

import { LoginComponent } from './login/login.component';
import { TemaComponent } from './tema/tema.component';

const routes: Routes = [

  {path:'', redirectTo:'entrar', pathMatch: 'full'},

  {path:'entrar', component: LoginComponent},
  {path:'cadastrar', component: CadastrarComponent},

  {path: 'inicio', component: InicioComponent},
  {path: 'tema', component: TemaComponent},

  {path:'tema-edit/:idTema', component: TemaEditComponent},
  {path:'tema-delete/:idTema', component: TemaDeleteComponent},

  {path:'postagem-edit/:id', component: PostagemEditComponent},
  {path:'postagem-delete/:id', component: PostagemDeleteComponent },
  {path:'user-edit/:id', component: UserEditComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
