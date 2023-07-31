import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';
import { Card } from '../card/card';
import { Auth } from './auth';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userServiceURL = "http://localhost:8080/api";
  private ID!: number;

  constructor(private http: HttpClient) { }

  login(auth: Auth): Observable<any> {
    return this.http.post<any>(this.userServiceURL + "/login", auth);
  }
  
  saveUser(user: User): Observable<User> {
    return this.http.post<User>(this.userServiceURL, user);
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userServiceURL);
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(this.userServiceURL + '/'+ id);
  }

  updateUser(user: User, id: number): Observable<User> {
    return this.http.put<User>(this.userServiceURL + '/' + id, user);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(this.userServiceURL+ '/' + id);
  }

  getUserCards(id: number): Observable<Card[]> {
    return this.http.get<Card[]>(this.userServiceURL + '/' + id + '/cards')
  }

  setID(id: number): void{
    this.ID = id;
  }

  getID(): number{
    return this.ID;
  }
}
