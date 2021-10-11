import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tema } from 'src/app/models/Tema';
import { TemaService } from 'src/app/services/tema.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-tema-edit',
  templateUrl: './tema-edit.component.html',
  styleUrls: ['./tema-edit.component.css']
})
export class TemaEditComponent implements OnInit {

  tema: Tema = new Tema()

  constructor(
    private temaService: TemaService,
    private router: Router,
    private routerAtual: ActivatedRoute
  ) { }

  ngOnInit(){
    if(environment.token == '') {
      alert("Sua sessão expirou, faça login novamente!")
      this.router.navigate(['/entrar'])
    }

    let id = this.routerAtual.snapshot.params['idTema']
    this.findByIdTema(id)
  }

  findByIdTema(id: number) {
    this.temaService.getByIdTema(id).subscribe((resp: Tema)=> {
      this.tema= resp
    })
  }

  atualizar() {
    this.temaService.putTema(this.tema).subscribe((resp: Tema)=> {
      this.tema = resp
      alert('Tema atualizado!')
      this.router.navigate(['/tema'])
    })
  }

}
