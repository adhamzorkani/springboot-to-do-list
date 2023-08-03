import { Component } from '@angular/core';
import { StorageService } from './user/_services/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'to do list';
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  
  constructor(private storageService: StorageService) {}

  ngOnInit():void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    
    if(this.isLoggedIn){
      const user = this.storageService.getUser();
      this.roles = user.roles;

      this.username = user.username;
    }
  }

  logout(): void {
    this.storageService.clean();
    window.location.reload();
  }
}
