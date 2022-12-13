import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {finalize} from "rxjs/operators";
import {AngularFireStorage} from "@angular/fire/storage";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {environment} from "../../environments/environment";
import {Product} from "../model/product";
import {ProductService} from "../product.service";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  selectedFile: File = null;
  downloadURL: Observable<string>;
  formProduct: FormGroup;
  product: Product;
  fb;

  constructor(private httpClient: HttpClient,
              private storage: AngularFireStorage,
              private formBuilder: FormBuilder,
              private productService: ProductService) {
  }

  ngOnInit(): void {
    this.formProduct = this.formBuilder.group({
      name: [],
      img: []
    })
  }


  addProduct() {
    this.productService.save(this.formProduct.value).subscribe(() => {

    })
  }

  saveImg(event: any) {
    const n = Date.now();
    const file = event.target.files[0];
    const filePath = `RoomsImages/${n}`;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(`RoomsImages/${n}`, file);
    task
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          this.downloadURL.subscribe(url => {
            this.formProduct.patchValue({img: url});
            console.log(url)
          });
        })
      )
      .subscribe(url => {
        if (url) {
          console.log(url);
        }
      });
  }

}

