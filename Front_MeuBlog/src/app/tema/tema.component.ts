import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { AlertasComponent } from '../alertas/alertas.component';
import { Tema } from '../models/Tema';
import { AlertasService } from '../services/alertas.service';
import { TemaService } from '../services/tema.service';

@Component({
  selector: 'app-tema',
  templateUrl: './tema.component.html',
  styleUrls: ['./tema.component.css']
})
export class TemaComponent implements OnInit {

  tema: Tema = new Tema()
  listaDeTemas: Tema[]

  constructor(
    private router: Router,
    private temaService: TemaService,
    private alertaService: AlertasService
  ) { }

  ngOnInit() {
    if(environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    if(environment.tipo != 'adm') {
      this.alertaService.showAlertInfo('Você Precisa ser ADM para acessar essa função!')
      this.router.navigate(['/inicio'])
    }

    this.findAllTemas()
  }

  findAllTemas() {
    this.temaService.getAllTema().subscribe((resp: Tema[])=> {
      this.listaDeTemas= resp
      this.router.navigate(['/inicio'])
    })
  }

  cadastrar() {
    this.temaService.postTema(this.tema).subscribe((resp: Tema)=> {
      this.tema= resp
      this.alertaService.showAlertSuccess('Tema cadastrado!')
      this.findAllTemas()
      this.tema= new Tema()
    })
  }



}
