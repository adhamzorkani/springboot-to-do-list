import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user!: User;

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get("id")!;
    if (id != 0) {
      this.userService.getUser(id).subscribe(user => this.user = user);
    } else {
      this.user = new User(0, "", "");
    }
  }

  saveUser(): void {
    this.userService.saveUser(this.user).subscribe(user => this.user = user);
    console.log("User registered successfully");
    this.router.navigate(["/users"]);
  }
}
