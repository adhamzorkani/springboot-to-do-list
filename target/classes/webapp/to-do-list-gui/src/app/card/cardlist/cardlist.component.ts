import { Component, OnInit } from '@angular/core';
import { Card } from '../card';
import { CardService } from '../card.service';

@Component({
  selector: 'app-cardlist',
  templateUrl: './cardlist.component.html',
  styleUrls: ['./cardlist.component.css']
})
export class CardlistComponent implements OnInit {
  title = 'To Do List';

  noCards = "No Cards Available";

  loadingMessage = "Loading Data, Please wait...";

  cards!: Card[];

  constructor(private cardService: CardService) {

  }

  ngOnInit(): void {
    this.getCards();
  }

  getCards(): void {
    this.cardService.getCards().subscribe(cards => this.cards = cards);
  }

  deleteCard(card: Card): void {
    if (confirm("Are you sure you want to delete " + card.title + " card?")) {
      this.cardService.deleteCard(card.id).subscribe(response => {
        alert(response.message);
        this.getCards();
      });
    }
  }
}
