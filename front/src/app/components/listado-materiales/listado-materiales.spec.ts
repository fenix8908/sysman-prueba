import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoMateriales } from './listado-materiales';

describe('ListadoMateriales', () => {
  let component: ListadoMateriales;
  let fixture: ComponentFixture<ListadoMateriales>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListadoMateriales]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListadoMateriales);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
