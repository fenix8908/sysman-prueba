import { CurrencyPipe, NgFor, NgIf } from '@angular/common';
import { MaterialService } from '../../services/material-service';
import { MaterialModel, MaterialResponse } from './../../models/material/material-model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listado-materiales',
  standalone: true,
  imports: [CurrencyPipe],
  templateUrl: './listado-materiales.html',
  styleUrl: './listado-materiales.css'
})
export class ListadoMateriales implements OnInit{
  materiales: MaterialModel[] = [];
  cargando = false;
  error: string | null = null;

  constructor(private materialService: MaterialService) { }

  ngOnInit(): void {
    this.obtenerMateriales();
  }

  obtenerMateriales(): void {
    this.cargando = true;
    this.error = null;
    this.materialService.obtnerMateriales().subscribe({
      next: (res: MaterialResponse) => {  // Agrega el tipo aquí
            this.materiales = res.response;  // Quita el 'as'
            console.log(this.materiales);
            this.cargando = false;
        },
      error:(err) => {
        this.error = 'Error al cargar los materiales';
        this.cargando = false;
      }
    });
  }

}
