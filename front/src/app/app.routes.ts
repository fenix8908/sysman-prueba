import { CrearMateriales } from './components/listado-materiales/crear-materiales/crear-materiales/crear-materiales';
import { Routes } from '@angular/router';
import { ListadoMateriales } from './components/listado-materiales/listado-materiales';

export const routes: Routes = [
  { path: 'listado', component: ListadoMateriales },
  { path: 'materiales/nuevo', component: CrearMateriales },
  { path: 'materiales/editar/:id', component: CrearMateriales },
  { path: '', redirectTo: '/listado', pathMatch: 'full' },
  { path: '**', redirectTo: '/listado' }
];
