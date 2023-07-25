import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Card } from './card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private cardServiceURL = "http://localhost:8080/api/users/cards"
  constructor(private http: HttpClient) { }

  getCards(): Observable<Card[]> {
    return this.http.get<Card[]>(this.cardServiceURL);
  }

  getCard(id: number): Observable<Card> {
    return this.http.get<Card>(this.cardServiceURL + '/' + id);
  }

  deleteCard(id: number): Observable<any> {
    return this.http.delete<any>(this.cardServiceURL + '/' + id);
  }

  saveCard(card: Card, id: number): Observable<Card> {
    return this.http.post<Card>(this.cardServiceURL + '/' + id, card);
  }
}
