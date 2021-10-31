import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tema } from 'src/app/models/Tema';
import { AlertasService } from 'src/app/services/alertas.service';
import { TemaService } from 'src/app/services/tema.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-tema-delete',
  templateUrl: './tema-delete.component.html',
  styleUrls: ['./tema-delete.component.css']
})
export class TemaDeleteComponent implements OnInit {

  tema: Tema = new Tema()
  idTema: number

  constructor(
    private temaService: TemaService,
    private router: Router,
    private routerAtivo: ActivatedRoute,
    private alertaService: AlertasService
  ) { }

  ngOnInit() {
    if(environment.token == '') {
      this.alertaService.showAlertInfo("Sua sessão expirou, faça login novamente!")
      this.router.navigate(['/entrar'])
    }

    this.idTema = this.routerAtivo.snapshot.params['idTema']
    this.findByIdTema(this.idTema)
  }

  findByIdTema(id: number) {
    this.temaService.getByIdTema(id).subscribe((resp: Tema)=> {
      this.tema = resp
    })
  }

  apagar() {
    this.temaService.deleteTema(this.idTema).subscribe(()=> {
        this.alertaService.showAlertSuccess('Tema deletado!')
        this.router.navigate(['/tema'])
    })
  }

}
