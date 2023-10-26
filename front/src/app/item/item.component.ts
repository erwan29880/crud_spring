import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FetchService } from '../fetch.service';
import { Commande } from '../modele/modele';


@Component({
  selector: 'app-item',
  templateUrl: './item.component.html'
})
export class ItemComponent {

  data!: Commande;

  constructor(private service: FetchService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    const id: string | null = this.route.snapshot.paramMap.get("id");
    if (id !== null) {
      this.service.getDataById(id).subscribe(o => {
        o.commandeId === null ? this.router.navigate(['/404']) : this.data = o;
      });
    } else {
      this.router.navigate(['/404']);
    }
  }

  onIndex() {
    this.router.navigate(['/']);
  }

  onDelete(event: MouseEvent, id:number) {
    this.service.deleteById(id.toString()).subscribe();
    setTimeout(() => {
      this.router.navigate(['/']);
    }, 400)
  }
}
