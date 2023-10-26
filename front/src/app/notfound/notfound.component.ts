import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notfound',
  templateUrl: './notfound.component.html'
})
export class NotfoundComponent {

  constructor(private router: Router){}

  onChangeUrl() : void {
    this.router.navigate(['/']);
  }
}
