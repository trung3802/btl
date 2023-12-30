import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


const PRODUCT_API = "http://localhost:8080/api/product/";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  getListProduct():Observable<any>{
    return this.http.get(PRODUCT_API,httpOptions);
  }

  getListProductNewest(num: number):Observable<any>{
    return this.http.get(PRODUCT_API + 'newest/' + num,httpOptions);
  }

  getListProductByPrice():Observable<any>{
    return this.http.get(PRODUCT_API + 'price',httpOptions);
  }

  getListRelatedProduct(id: number):Observable<any>{
    return this.http.get(PRODUCT_API + 'related/' + id,httpOptions);
  }

  getListByCategory(id: number):Observable<any>{
    return this.http.get(PRODUCT_API + 'category/' + id,httpOptions);
  }

  searchProduct(keyword: string):Observable<any>{
    let params = new HttpParams();
    params =params.append('keyword',keyword)
    return this.http.get(PRODUCT_API + 'search',{params: params});
  }

  getListByPriceRange(id: number, min:number, max: number):Observable<any>{
    let params = new HttpParams();
    params = params.append('id',id);
    params = params.append('min',min);
    params = params.append('max',max);
    return this.http.get(PRODUCT_API + 'range',{params: params})
  }

  getProdct(id: number):Observable<any>{
    return this.http.get(PRODUCT_API + id,httpOptions);
  }

  createProduct(name:string,description: string,price: string,quantity:number,categoryId: number,mota: string,imageIds: Array<string>):Observable<any>{
    return this.http.post(PRODUCT_API +'create',{name,description,price,quantity,categoryId,mota,imageIds},httpOptions);
  }

  updateProduct(id: number,name:string,description: string,price: string,quantity:number,categoryId: number,mota: string,imageIds: Array<string>):Observable<any>{
    return this.http.put(PRODUCT_API + 'update/'+id,{name,description,price,quantity,categoryId,mota,imageIds},httpOptions);
  }

  deleteProduct(id:number):Observable<any>{
    return this.http.delete(PRODUCT_API + 'delete/' + id,httpOptions);
  }
// thống kê
  getTotalProducts(): Observable<number> {
    // Gọi API để lấy danh sách sản phẩm và sau đó tính toán tổng số sản phẩm
    return this.getListProduct().pipe(
      map(products => products.length)
    );
  }



}
