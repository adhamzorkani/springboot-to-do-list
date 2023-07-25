import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { CardComponent } from './card/card.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'
import { UserService } from './user/user.service';
import { UserListComponent } from './user/user-list/user-list.component';
import { CardlistComponent } from './card/cardlist/cardlist.component';
import { UsercardsComponent } from './user/usercards/usercards.component';
import { UserLoginComponent } from './user/user-login/user-login.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    CardComponent,
    UserListComponent,
    CardlistComponent,
    UsercardsComponent,
    UserLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
