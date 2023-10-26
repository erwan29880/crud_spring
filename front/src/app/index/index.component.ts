import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms'; 
import { Commande } from '../modele/modele';
import { Router } from '@angular/router';
import { FetchService } from '../fetch.service';
import { numberValidator } from '../numberValidator';
import { Observable, BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
})
export class IndexComponent implements OnInit {

  commandess!: Commande[];
  infos!: FormGroup;
  prods!: FormGroup;
  commandesPost!: Commande;
  produits: string[] = [];
  commandes = new BehaviorSubject<Commande[]>([]);
  commandes$ = this.commandes.asObservable();

  constructor(private formBuilder: FormBuilder, private service: FetchService, private router: Router){}

  ngOnInit() {
    this.service.getData().subscribe(res => this.commandes.next(res));
    this.commandes$.subscribe(res => this.commandess = res);

    this.prods = this.formBuilder.group({
      produit: new FormControl("oeuf")
    })

    this.infos = this.formBuilder.group({
      nom : new FormControl("azer", [
        Validators.required,
        Validators.pattern("^[a-zA-Z àéèùçîïôö'-]*$"),
        Validators.maxLength(40)
      ]),
      prenom : new FormControl("zert", [
        Validators.required,
        Validators.pattern("^[a-zA-Z àéèùçîïôö'-]*$"),
        Validators.maxLength(40)
      ]),
      mobile : new FormControl(1234567898, [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
        Validators.minLength(10),
        Validators.maxLength(10)
      ]),
      email : new FormControl("jefaisuntest@gmail.com", [
        Validators.required,
        Validators.email
      ])
    })
  }

  onClickOnItem(event:MouseEvent, id: number) {
    console.log(id)
      this.router.navigate(['/commande', id ]);
  }

  onSubmit() {
    const date = this.dateFormatter();
    const produitsSet = new Set(this.produits);
    const produits = Array.from(produitsSet);
    this.commandesPost = {...this.infos.value, date: date, produits:produits, commandeId:0}
    
    this.service.postData(this.commandesPost).subscribe();
    setTimeout(() => {
      this.service.getData().subscribe(res => this.commandes.next(res));
    }, 700);
  }

  onAddProduct() {
    console.log(this.prods.value.produit)
    this.produits.push(this.prods.value.produit)
  }

  dateFormatter() :string {
    const date: Date = new Date();
    let month: string = date.getMonth().toString();
    let day: string = date.getDay().toString();
    if (month.length === 1) month = "0" + month;
    if (day.length === 1) day = "0" + day;
    const dateFormatted = date.getFullYear().toString() + '-' + month + '-' + day;
    return dateFormatted;
  }

}
