import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../user';
import { Observable } from 'rxjs';
import { Card } from '../../card/card';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userServiceURL = "http://localhost:8080/api";
  private ID!: number;

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userServiceURL + + '/admin/users');
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(this.userServiceURL + '/user/' + id);
  }

  updateUser(user: User, id: number): Observable<User> {
    return this.http.put<User>(this.userServiceURL + '/admin/user/' + id, user);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(this.userServiceURL + '/admin/user/' + id);
  }

  getUserCards(id: number): Observable<Card[]> {
    return this.http.get<Card[]>(this.userServiceURL + " /user/" + id + "/cards");
  }

  setID(id: number): void {
    this.ID = id;
  }

  getID(): number {
    return this.ID;
  }
}
