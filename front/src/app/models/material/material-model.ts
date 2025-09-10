export interface DepartamentoModel {
  id: number;
  codigo: string;
  nombre: string;
}

export interface CiudadModel {
  id: number;
  codigo: number;
  nombre: string;
  departamento: DepartamentoModel;
}

export interface MaterialModel {
  id?: number;
  nombre: string;
  descripcion: string;
  tipo: string;
  precio: number;
  fechaCompra: string;
  fechaVenta?: string;
  estado: string;
  ciudad: CiudadModel;  // Cambiado de ciudadId: number
}

export interface MaterialResponse {
  mensaje: string;
  exito: boolean;
  response: MaterialModel[];
}
