import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Tema } from '../models/Tema';

@Injectable({
  providedIn: 'root'
})
export class TemaService {

  constructor(private http: HttpClient) { }

  token= {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }
    getAllTema(): Observable<Tema[]>{
      return this.http.get<Tema[]>('https://projetoblogdocris.herokuapp.com/v1.5/blog/theme/allthemes', this.token)
    }

    getByIdTema(idTema: number): Observable<Tema>{
        return this.http.get<Tema>(`https://projetoblogdocris.herokuapp.com/v1.5/blog/theme/searchid/${idTema}`, this.token)
    }

    getByNomeTema(tema: string): Observable<Tema[]> {
      return this.http.get <Tema[]> (`https://projetoblogdocris.herokuapp.com/v1.5/blog/theme/searchtheme/${tema}`, this.token)
    }

    postTema(tema: Tema): Observable<Tema> {
      return this.http.post<Tema>('https://projetoblogdocris.herokuapp.com/v1.5/blog/theme/save', tema, this.token)
    }

    putTema(tema: Tema): Observable<Tema> {
      return this.http.put<Tema>('https://projetoblogdocris.herokuapp.com/v1.5/blog/theme/update', tema, this.token)
    }

    deleteTema(idTema: number) {
      return this.http.delete(`https://projetoblogdocris.herokuapp.com/v1.5/blog/theme/delete/${idTema}`, this.token)
    }

}
