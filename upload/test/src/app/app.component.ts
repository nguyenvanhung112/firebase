import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {AngularFireStorage} from "@angular/fire/storage";
import {finalize} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {ProductService} from "./product.service";
import {Product} from "./model/product";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  productList: Product[]
  title = 'test';
  selectedFile: File = null;
  fb;
  downloadURL: Observable<string>;

  constructor(private storage: AngularFireStorage,
              private  productService: ProductService) {
  }

  ngOnInit(): void {
    this.productService.findAll().subscribe(data => {
      this.productList = data
    })

  }
}
