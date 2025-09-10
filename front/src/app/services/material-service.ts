import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MaterialModel, MaterialResponse } from '../models/material/material-model';

@Injectable({
  providedIn: 'root',
})
export class MaterialService {
  private baseUrl = 'http://localhost:8080/sysman/materiales';

  constructor(private http:HttpClient) { }

  obtnerMateriales(): Observable<MaterialResponse> {
    return this.http.get<MaterialResponse>(`${this.baseUrl}`);
  }


  filtrarPorTipoYFecha(tipo: string, fechaDesde: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/filtrar/${tipo}/${fechaDesde}`);
  }

  obtenerPorCiudad(ciudadId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/ciudad/${ciudadId}`);
  }

  guardar(material: MaterialModel): Observable<any> {
    return this.http.post(`${this.baseUrl}/guardar`, material);
  }

  actualizar(id: number, material: MaterialModel): Observable<any> {
    return this.http.put(`${this.baseUrl}/actualizar/${id}`, material);
  }

}
