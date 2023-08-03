import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { Card } from 'src/app/card/card';
import { CardService } from 'src/app/card/card.service';
import { User } from '../user';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from '../_services/storage.service';

@Component({
  selector: 'app-usercards',
  templateUrl: './usercards.component.html',
  styleUrls: ['./usercards.component.css']
})
export class UsercardsComponent implements OnInit {
  title = ' Card List';

  noCards = "No Cards Available";

  loadingMessage = "Loading Data, Please wait...";

  cards!: Card[];


  user!: User;

  constructor(private userService: UserService, private cardService: CardService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.user = this.storageService.getUser();
    console.log(this.user);
    this.getUserCards();
  }

  getUserCards(): void {
    this.userService.getUserCards(this.user.id).subscribe(cards => this.cards = cards);
    this.userService.getUser(this.user.id).subscribe(user => this.user = user)
  }

  deleteCard(card: Card): void {
    if (confirm("Are you sure you want to delete " + card.title + " card?")) {
      this.cardService.deleteCard(card.id).subscribe(response => {
        alert(response.message);
        this.getUserCards();
      });
    }
  }
}
