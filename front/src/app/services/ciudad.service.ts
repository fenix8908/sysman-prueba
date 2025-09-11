import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CiudadService {

  baseUrl = 'http://localhost:8080/sysman/ciudades';

  constructor(private http: HttpClient) { }

  obtenerCiudades() {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }

}
