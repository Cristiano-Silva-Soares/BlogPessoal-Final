import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagem } from 'src/app/models/Postagem';
import { AlertasService } from 'src/app/services/alertas.service';
import { PostagemService } from 'src/app/services/postagem.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-postagem-delete',
  templateUrl: './postagem-delete.component.html',
  styleUrls: ['./postagem-delete.component.css']
})
export class PostagemDeleteComponent implements OnInit {

  postagem: Postagem = new Postagem()
  idPost: number


  constructor(
    private router: Router,
    private routerAtivo: ActivatedRoute,
    private postagemService: PostagemService,
    private alertaService: AlertasService
  ) { }

  ngOnInit() {

    window.scroll(0,0)

    if(environment.token == '') {
      this.alertaService.showAlertInfo("Sua sessão expirou, faça login novamente!")
      this.router.navigate(['/entrar'])
    }

    this.idPost = this.routerAtivo.snapshot.params['id']
    this.findByIdPostagem(this.idPost)
  }

  findByIdPostagem(id: number) {
    this.postagemService.getByIdPostagem(id).subscribe((resp: Postagem)=> {
      this.postagem = resp
    })
  }


  apagar() {
    this.postagemService.deletePostagem(this.idPost).subscribe(()=> {
      this.alertaService.showAlertSuccess('Postagem apagada!')
      this.router.navigate(['/inicio'])
    })
  }

}

