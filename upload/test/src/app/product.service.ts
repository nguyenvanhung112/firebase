import { Injectable } from '@angular/core';
import {Product} from "./model/product";
import {Observable} from "rxjs";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  save(product: Product): Observable<Product> {
   return  this.httpClient.post<Product>(environment.product,product)
  }

  findAll(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(environment.product)
  }
}
