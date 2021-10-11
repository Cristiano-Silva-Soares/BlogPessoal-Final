import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';


import { User } from '../models/User';
import { UserDto } from '../models/UserDto';




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  token= {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  entrar(userLogin: UserDto): Observable<UserDto> {
    return this.http.put<UserDto>('https://projetoblogdocris.herokuapp.com/v1.5/blog/users/login', userLogin)
  }

  getByIdUser(id: number): Observable<User> {
    return this.http.get<User>(`https://projetoblogdocris.herokuapp.com/v1.5/blog/users/searchid/${id}`)
  }

  cadastrar(user: User): Observable<User> {
    return this.http.post<User>('https://projetoblogdocris.herokuapp.com/v1.5/blog/users/save', user)
  }

  atualizar(user: User): Observable<User> {
    return this.http.put<User> ('https://projetoblogdocris.herokuapp.com/v1.5/blog/users/update', user, this.token)
  }

  logado() {
    let ok: boolean= false

    if(environment.token != '') {
      ok= true
    }

    return ok;
  }

}
