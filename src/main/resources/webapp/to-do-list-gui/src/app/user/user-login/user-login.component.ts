import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { Auth } from '../auth';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  auth!: Auth
  user!: User;
  id!: number;
  constructor(private userService: UserService, private router: Router) {

  }

  ngOnInit(): void {
    this.user = new User(0, '', '', false, []);
  }

  login(): void {
    this.userService.login(this.auth).subscribe((res) => {
        this.id = res.id;
        this.userService.setID(this.id);
        this.router.navigate(['/user/cards']);
      });
  }
}
