export interface MaterialModel {
  id?: number;
  nombre: string;
  descripcion: string;
  tipo: string;
  precio: number;
  fechaCompra: string;
  fechaVenta?: string;
  estado: string;
  ciudadId: number;
}
