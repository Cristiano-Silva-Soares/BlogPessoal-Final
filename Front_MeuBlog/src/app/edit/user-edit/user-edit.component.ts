import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { AlertasService } from 'src/app/services/alertas.service';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User = new User()
  userId: number
  confirmarSenha: string
  tipoUsuarios: string

  constructor(
    private routerAtiva: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private alertaService: AlertasService
  ) { }

  ngOnInit() {

    window.scroll(0,0)

    if(environment.token == '') {
      this.alertaService.showAlertInfo("Sua sessão expirou, faça login novamente!")
      this.router.navigate(['/entrar'])
    }

    this.userId = this.routerAtiva.snapshot.params['id']
    this.findByIdUser(this.userId)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value

  }

  tipoUser(event: any) {
    this.tipoUsuarios = event.target.value
  }

  atualizar() {

    this.user.tipoUsuario = this.tipoUsuarios

      if(this.user.senha != this.confirmarSenha) {
          alert('As senhas estão incorretas!')
      } else {
          this.authService.atualizar(this.user).subscribe((resp: User)=> {
            this.user = resp
            this.router.navigate(['/inicio'])
            this.alertaService.showAlertSuccess('Usuário atualizado, faça o login novamente!')
            environment.token=''
            environment.nomeUsuario=''
            environment.foto=''
            environment.idUsuario= 0
            environment.tipo= ''
            this.router.navigate(['/login'])
          })
      }

  }

  findByIdUser(id: number) {
    this.authService.getByIdUser(id).subscribe((resp: User)=> {
      this.user = resp

    })
  }

}
