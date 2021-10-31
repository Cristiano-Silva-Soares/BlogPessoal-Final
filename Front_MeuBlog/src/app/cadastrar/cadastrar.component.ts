import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/User';
import { AlertasService } from '../services/alertas.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

    user: User= new User
    confirmarSenha: string
    tipoUsuarios: string

    constructor(
      private authService: AuthService,
      private router: Router,
      private alertaService: AlertasService

      ) { }

    ngOnInit() {
      window.scroll(0,0)
    }

    confirmSenha(event: any) {
      this.confirmarSenha= event.target.value
    }

    tipoUser(event: any) {
      this.tipoUsuarios= event.target.value
    }

    cadastrar() {
      this.user.tipoUsuario = this.tipoUsuarios

      if(this.user.senha != this.confirmarSenha) {
          this.alertaService.showAlertDanger('As senhas estão incorretas!')
      } else {
          this.authService.cadastrar(this.user).subscribe((resp: User)=> {
            this.user = resp
            this.router.navigate(['/entrar'])
            this.alertaService.showAlertSuccess('Usuário cadastrado!')
          })
      }
    }

  }
