import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialService } from '../../../../services/material-service';
import { CiudadModel, MaterialModel } from '../../../../models/material/material-model';
import { Subscription } from 'rxjs';
import { CiudadService } from '../../../../services/ciudad.service';

@Component({
  selector: 'app-crear-materiales',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './crear-materiales.html',
  styleUrl: './crear-materiales.css'
})
export class CrearMateriales {

  materialForm: FormGroup;
  isEditMode = false;
  materialId: number | null = null;
  loading = false;
  submitted = false;
  error: string | null = null;
  private subscriptions = new Subscription();

  // Opciones para los selects
  tiposMaterial = [
    { value: 'CONSTRUCCION', label: 'Construcción' },
    { value: 'ELECTRICIDAD', label: 'Electricidad' },
    { value: 'FONTANERIA', label: 'Fontanería' },
    { value: 'HERRAMIENTA', label: 'Herramienta' }
  ];

  estadosMaterial = [
    { value: 'ACTIVO', label: 'Activo' },
    { value: 'DISPONIBLE', label: 'Disponible' },
    { value: 'ASIGNADO', label: 'Asignado' }
  ];

  ciudades: CiudadModel[] = []; // Se cargarán desde el servicio

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private materialService: MaterialService,
    private ciudadService: CiudadService
  ) {
    this.materialForm = this.createForm();
  }

  ngOnInit(): void {
    this.loadCiudades();

    // Verificar si estamos en modo edición
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.materialId = +id;
      this.loadMaterialData(this.materialId);
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  createForm(): FormGroup {
    return this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(100)]],
      descripcion: ['', [Validators.required, Validators.maxLength(500)]],
      tipo: ['', Validators.required],
      precio: [0, [Validators.required, Validators.min(0)]],
      fechaCompra: ['', Validators.required],
      fechaVenta: [''],
      estado: ['', Validators.required],
      ciudadId: ['', Validators.required]
    });
  }

  loadCiudades(): void {
    this.subscriptions.add(
      this.ciudadService.obtenerCiudades().subscribe({
        next: (ciudades: CiudadModel[]) => {
          this.ciudades = ciudades;
        },
        error: () => {
          console.error('Error al cargar ciudades:', this.error);
          this.error = 'Error al cargar las ciudades';
        }
      })
    );
  }

  loadMaterialData(id: number): void {
    this.loading = true;
    this.subscriptions.add(
      this.materialService.obtenerMaterialPorId(id).subscribe({
        next: (material: MaterialModel) => {
          this.materialForm.patchValue({
             ...material,
            fechaCompra: this.formatDateForInput(material.fechaCompra),
            fechaVenta: material.fechaVenta ? this.formatDateForInput(material.fechaVenta) : '',
            ciudadId: material.ciudad.id
          });
          this.loading = false;
        },
        error: () => {
          console.error('Error al cargar el material:',this.error);
          this.error = 'Error al cargar el material';
          this.loading = false;
        }
      })
    );
  }

  formatDateForInput(dateString: string): string {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toISOString().split('T')[0];
  }

  get f() { return this.materialForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    this.error = null;

    if (this.materialForm.invalid) {
      return;
    }

    this.loading = true;
    const materialData: MaterialModel = this.materialForm.value;

    if (this.isEditMode && this.materialId) {
      this.subscriptions.add(
        this.materialService.actualizar(this.materialId, materialData).subscribe({
          next: () => {
            this.loading = false;
            this.router.navigate(['/listado']);
          },
          error: (error) => {
            console.error('Error al actualizar el material:', error);
            this.error = 'Error al actualizar el material';
            this.loading = false;
          }
        })
      );
    } else {
      this.subscriptions.add(
        this.materialService.guardar(materialData).subscribe({
          next: () => {
            this.loading = false;
            this.router.navigate(['/listado']);
          },
          error: (error) => {
            console.error('Error al guardar el material:', error);
            this.error = 'Error al guardar el material';
            this.loading = false;
          }
        })
      );
    }
  }

  onCancel(): void {
    this.router.navigate(['/listado']);
  }

}
