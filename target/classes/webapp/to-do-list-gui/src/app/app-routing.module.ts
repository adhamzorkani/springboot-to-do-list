import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { CardComponent } from './card/card.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { CardlistComponent } from './card/cardlist/cardlist.component';
import { UsercardsComponent } from './user/usercards/usercards.component';
import { UserLoginComponent } from './user/user-login/user-login.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: UserLoginComponent},
  { path: 'register', component: UserComponent },
  { path: 'edit/:id', component: UserComponent },
  { path: 'users', component: UserListComponent },
  { path: 'card', component: CardComponent },
  { path: 'cards', component: CardlistComponent},
  { path: 'edit/card/:id', component: CardComponent},
  { path: 'user/cards', component: UsercardsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
