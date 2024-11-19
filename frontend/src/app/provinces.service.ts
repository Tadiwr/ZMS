import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SERVER_BASE_URL } from './utils/contants';

export type Province = {
  id: number
  name: string,
}

@Injectable({
  providedIn: 'root'
})
export class ProvincesService {

  constructor(private http: HttpClient) {}

  private baseUrl = `${SERVER_BASE_URL}/provinces`;

  public getAllProvinces() {
    const reqUrl = `${this.baseUrl}`;
    
    return this.http.get(reqUrl);
  }

}
