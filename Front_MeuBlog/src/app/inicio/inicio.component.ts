import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../models/Postagem';
import { Tema } from '../models/Tema';
import { User } from '../models/User';
import { AuthService } from '../services/auth.service';
import { PostagemService } from '../services/postagem.service';
import { TemaService } from '../services/tema.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem()
  tema: Tema = new Tema()
  listaPostagens: Postagem[]
  listaTemas: Tema[]
  idTema: number

  user: User = new User()
  idUser = environment.idUsuario

  constructor(
    private router: Router,
    private postagemService: PostagemService,
    private temaService: TemaService,
    private authService: AuthService
  ) { }

  ngOnInit() {

    if(environment.token == '') {
      alert("Sua sessão expirou, faça login novamente!")
      this.router.navigate(['/entrar'])
    }

    this.getAllTemas()
    this.getAllPostagens()
  }

  getAllTemas(){
    this.temaService.getAllTema().subscribe((resp: Tema[])=> {
      this.listaTemas = resp
    })
  }

  findByIdTema() {
      this.temaService.getByIdTema(this.idTema).subscribe((resp: Tema)=> {
        this.tema = resp
      })
  }

  getAllPostagens() {
    this.postagemService.getAllPostagens().subscribe((resp: Postagem[])=> {
        this.listaPostagens = resp
    })
  }

  findByIdUser() {
    this.authService.getByIdUser(this.idUser).subscribe((resp: User)=> {
        this.user = resp
    })
  }

  publicar() {
    this.tema.idTema = this.idTema
    this.postagem.temaRelacionado = this.tema

    this.user.idUsuario = this.idUser
    this.postagem.criador = this.user

    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem)=> {
      this.postagem = resp
      alert('Postagem realizada!')
      this.postagem = new Postagem()
      this.getAllPostagens()
    })
  }



}
