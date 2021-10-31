import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { UserDto } from '../models/UserDto';
import { AlertasService } from '../services/alertas.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin: UserDto= new UserDto
  constructor(
    private authService: AuthService,
    private router: Router,
    private alertaService: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
  }

  entrar() {
    this.authService.entrar(this.userLogin).subscribe((resp: UserDto)=> {
      this.userLogin = resp

      environment.token= this.userLogin.token
      environment.nomeUsuario = this.userLogin.nomeUsuario
      environment.foto = this.userLogin.foto
      environment.idUsuario = this.userLogin.idUsuario
      environment.tipo = this.userLogin.tipoUsuario

      console.log(environment.token)
      console.log(environment.nomeUsuario)
      console.log(environment.foto)
      console.log(environment.idUsuario)
      console.log(environment.tipo)

      this.router.navigate(['/inicio'])
    }, erro => {
      if(erro.status == 500) {
        this.alertaService.showAlertDanger('E-mail ou senha estão incorretos!')
      } else if (erro.status == 400) {

        this.alertaService.showAlertDanger('E-mail ou senha estão incorretos!')

      }





    })
  }

}
