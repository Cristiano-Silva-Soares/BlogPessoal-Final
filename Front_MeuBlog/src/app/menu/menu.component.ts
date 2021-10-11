import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

nome = environment.nomeUsuario
foto = environment.foto
id= environment.idUsuario


  constructor(
    private router: Router
  ) { }

  ngOnInit() {
  }

  sair() {
    this.router.navigate(['/entrar'])
    environment.token= ''
    environment.nomeUsuario= ''
    environment.foto= ''
    environment.idUsuario= 0
  }

}
