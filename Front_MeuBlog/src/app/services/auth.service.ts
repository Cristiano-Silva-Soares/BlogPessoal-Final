import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';


import { User } from '../models/User';
import { UserDto } from '../models/UserDto';




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  entrar(userLogin: UserDto): Observable<UserDto> {
    return this.http.put<UserDto>('https://projetoblogdocris.herokuapp.com/v1.5/blog/users/login', userLogin)
  }

  cadastrar(user: User): Observable<User> {
    return this.http.post<User>('https://projetoblogdocris.herokuapp.com/v1.5/blog/users/save', user)
  }

  logado() {
    let ok: boolean= false

    if(environment.token != '') {
      ok= true
    }

    return ok;
  }

}
