import { Injectable } from '@angular/core';
import { Commande } from './modele/modele';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, map } from 'rxjs'


@Injectable({
  providedIn: 'root'
})
export class FetchService {

  private port: string = "80";

  constructor(private http: HttpClient) {}

  getData() : Observable<Commande[]>{
    return this.http.get<Commande[]>("http://localhost:"+this.port+"/commande").pipe(
      map(data => data.map(el => {
        el.produits = Array.from(new Set(el.produits))
        return el;
      }))
    );
  }

  getDataById(id:string) : Observable<Commande>{
    return this.http.get<Commande>("http://localhost:"+this.port+"/commande/"+id).pipe(
      map(data => {
        data.produits = Array.from(new Set(data.produits))
        return data;
      })
    );
  }

  postData(commande: Commande) : Observable<Commande>{
    return this.http.post<any>("http://localhost:"+this.port+"/commande", commande);
  }

  deleteById(id:string) : Observable<Commande> {
    return this.http.delete<Commande>("http://localhost:"+this.port+"/commande/"+id);
  }
}
