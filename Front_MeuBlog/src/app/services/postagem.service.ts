import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../models/Postagem';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  constructor(private http: HttpClient) { }

  token= {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllPostagens(): Observable<Postagem[]>{
    return this.http.get<Postagem[]>('https://projetoblogdocris.herokuapp.com/v1.5/blog/post/allposts', this.token)
  }

  getByIdPostagem(id: number): Observable<Postagem> {
    return this.http.get<Postagem>(`https://projetoblogdocris.herokuapp.com/v1.5/blog/post/searchid/${id}`, this.token )
  }

  getByTitulo(titulo: string): Observable<Postagem[]> {
    return this.http.get<Postagem[]>(`https://projetoblogdocris.herokuapp.com/v1.5/blog/post/searchtitle/${titulo}`, this.token )
  }

  postPostagem(postagem: Postagem): Observable<Postagem>{
    return this.http.post<Postagem>('https://projetoblogdocris.herokuapp.com/v1.5/blog/post/save', postagem, this.token)

  }

  putPostagem(postagem: Postagem): Observable<Postagem> {
    return this.http.put<Postagem>('https://projetoblogdocris.herokuapp.com/v1.5/blog/post/update', postagem, this.token)
}

deletePostagem(id: number) {
  return this.http.delete(`https://projetoblogdocris.herokuapp.com/v1.5/blog/post/delete/${id}`, this.token)
}

}
