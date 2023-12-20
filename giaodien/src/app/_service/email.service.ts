import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


const PRODUCT_API = "http://localhost:8080/api/user/verify-account";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EmailService {
    constructor(private http: HttpClient) { }

    verifyEmailAndOTP(params: HttpParams): Observable<any> {
      // Sử dụng HttpParams làm tham số cho URL
      const options = { params };
    
      return this.http.put(PRODUCT_API, null, options);
    }
  }