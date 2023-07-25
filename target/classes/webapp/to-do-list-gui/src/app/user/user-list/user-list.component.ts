import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  title = "User List";

  noUsers = "No Cards Available";

  loadingMessage = "Loading Data, Please wait...";

  users!: User[];

  constructor(private userService: UserService) {

  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(userList => this.users = userList);
  }

  deleteUser(user: User): void {
    if (confirm("Are you sure you want to delete this user " + user.name + "?")) {
      this.userService.deleteUser(user.id).subscribe(response => {
        alert(response.message);
        this
      })
    }
  }
}
