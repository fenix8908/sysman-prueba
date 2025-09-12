import { CurrencyPipe } from '@angular/common';
import { MaterialService } from '../../services/material-service';
import { MaterialModel, MaterialResponse } from './../../models/material/material-model';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listado-materiales',
  standalone: true,
  imports: [CurrencyPipe, FormsModule, RouterLink],
  templateUrl: './listado-materiales.html',
  styleUrl: './listado-materiales.css',
})
export class ListadoMateriales implements OnInit {
  materiales: MaterialModel[] = [];
  materialesFiltrados: MaterialModel[] = [];
  cargando = false;
  error: string | null = null;

  // Filtros
  filtroCiudad: string = '';
  filtroTipo: string = '';
  filtroFechaCompra: string = '';

  // Opciones para los selects
  ciudades: string[] = [];
  tipos: string[] = [];

  constructor(private materialService: MaterialService) {}

  ngOnInit(): void {
    this.obtenerMateriales();
  }

  obtenerMateriales(): void {
    this.cargando = true;
    this.error = null;
    this.materialService.obtnerMateriales().subscribe({
      next: (res: MaterialResponse) => {
        // Agrega el tipo aquí
        this.materiales = res.response;
        this.materialesFiltrados = [...this.materiales];

        // Extraer ciudades y tipos únicos para los filtros
        this.extraerOpcionesFiltros();

        this.cargando = false;
      },
      error: (err) => {
        this.error = 'Error al cargar los materiales';
        this.cargando = false;
      },
    });
  }

  extraerOpcionesFiltros(): void {
    this.ciudades = [...new Set(this.materiales.map((m) => m.ciudad.nombre))].sort();
    this.tipos = [...new Set(this.materiales.map((m) => m.tipo))].sort();
  }

  aplicarFiltros(): void {
    this.materialesFiltrados = this.materiales.filter((material) => {
      // Filtro por ciudad
      const coincideCiudad =
        this.filtroCiudad === '' ||
        material.ciudad.nombre.toLowerCase().includes(this.filtroCiudad.toLowerCase());

      // Filtro por tipo
      const coincideTipo =
        this.filtroTipo === '' || material.tipo.toLowerCase() === this.filtroTipo.toLowerCase();

      // Filtro por fecha de compra
      let coincideFecha = true;
      if (this.filtroFechaCompra) {
        const fechaMaterial = new Date(material.fechaCompra);
        const fechaFiltro = new Date(this.filtroFechaCompra);
        console.log('Fecha filtro', fechaFiltro.toDateString());

        coincideFecha = fechaMaterial.toDateString() === fechaFiltro.toDateString();
      }

      return coincideCiudad && coincideTipo && coincideFecha;
    });
  }

  limpiarFiltros(): void {
    this.filtroCiudad = '';
    this.filtroTipo = '';
    this.filtroFechaCompra = '';
    this.materialesFiltrados = [...this.materiales];
  }

  eliminarMaterial(id: number | undefined): void {
    if (id === undefined) {
      alert('ID de material no válido');
      return;
    }

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción no se puede deshacer.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this.materialService.eliminarMaterial(id).subscribe({
          next: (res: MaterialResponse) => {
            Swal.fire('Eliminado', 'El material ha sido eliminado.', 'success');
            this.obtenerMateriales();
          },
          error: (err) => {
            this.error = 'Error al eliminar el material';
            Swal.fire('Error', 'No se pudo eliminar el material.', err.message);
          },
        });
      }
    });
  }
}
