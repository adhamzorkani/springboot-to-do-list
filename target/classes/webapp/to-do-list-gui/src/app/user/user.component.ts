import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { AuthService } from './_services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from './_services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user!: User;

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.getUser();
  }

  onSubmit(): void {
    console.log(this.user);

    this.authService.register(this.user).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    })
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get("id")!;
    if (id != 0) {
      this.userService.getUser(id).subscribe(user => this.user = user);
    } else {
      this.user = new User(0, "", "", false, []);
    }
  }

  // saveUser(): void {
  //   this.userService.saveUser(this.user).subscribe(user => this.user = user);
  //   console.log("User registered successfully");
  //   this.router.navigate(["/users"]);
  // }
}
