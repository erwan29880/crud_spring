import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotfoundComponent } from './notfound/notfound.component';
import { IndexComponent } from './index/index.component';
import { ItemComponent } from './item/item.component';

const routes: Routes = [
  {path: "", component: IndexComponent},
  {path: "commande/:id", component: ItemComponent},
  {path: "**", pathMatch: "full", component: NotfoundComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }