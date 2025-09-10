import { Component, signal } from '@angular/core';
import { ListadoMateriales } from './components/listado-materiales/listado-materiales';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [RouterOutlet]
})
export class App {
  protected readonly title = signal('front');
}
