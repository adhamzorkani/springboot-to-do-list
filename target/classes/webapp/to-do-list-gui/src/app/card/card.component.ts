import { Component, OnInit } from '@angular/core';
import { Card } from './card';
import { CardService } from './card.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user/user';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit{
  card!: Card;

  user!: User;

  id!: number;

  constructor(private cardService: CardService, private router: Router,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.getCard();
  }

  getCard(): void {
    const id = +this.route.snapshot.paramMap.get("id")!;
    if (id != 0) {
      this.cardService.getCard(id).subscribe(card => this.card = card);
    } else {
      this.card = new Card(0, "", "", "", 0 , 0, "", this.user);
    }
  }

  saveCard(): void {
    this.cardService.saveCard(this.card, this.id).subscribe(card => this.card = card);
    console.log('Card Saved successfully');
    this.router.navigate(['/cards']);
  }
}
