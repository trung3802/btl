import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const ORDERCT_API = "http://localhost:8080/api/orderdetail/";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class OrderChiTietService {
  constructor(private http: HttpClient) { }  


  // getOrderDetailsByOrderId(id: number):Observable<any>{
  //   return this.http.get(ORDERCT_API + `chitiet/${id}`,httpOptions);

    
  // }
  getOrderDetailsByOrderId(id: number): Observable<any> {
    const url = `${ORDERCT_API}chitiet/${id}`;
    console.log('URL:', url);
    return this.http.get(url, httpOptions);
  }
  

  getListOrderCT():Observable<any>{
    return this.http.get(ORDERCT_API,httpOptions);
  }


}
