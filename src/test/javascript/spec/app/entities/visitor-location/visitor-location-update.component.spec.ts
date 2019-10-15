import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RaysRentalsTestModule } from '../../../test.module';
import { VisitorLocationUpdateComponent } from 'app/entities/visitor-location/visitor-location-update.component';
import { VisitorLocationService } from 'app/entities/visitor-location/visitor-location.service';
import { VisitorLocation } from 'app/shared/model/visitor-location.model';

describe('Component Tests', () => {
  describe('VisitorLocation Management Update Component', () => {
    let comp: VisitorLocationUpdateComponent;
    let fixture: ComponentFixture<VisitorLocationUpdateComponent>;
    let service: VisitorLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RaysRentalsTestModule],
        declarations: [VisitorLocationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(VisitorLocationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VisitorLocationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VisitorLocationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VisitorLocation('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new VisitorLocation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
