import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RaysRentalsTestModule } from '../../../test.module';
import { VisitorLocationDeleteDialogComponent } from 'app/entities/visitor-location/visitor-location-delete-dialog.component';
import { VisitorLocationService } from 'app/entities/visitor-location/visitor-location.service';

describe('Component Tests', () => {
  describe('VisitorLocation Management Delete Component', () => {
    let comp: VisitorLocationDeleteDialogComponent;
    let fixture: ComponentFixture<VisitorLocationDeleteDialogComponent>;
    let service: VisitorLocationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RaysRentalsTestModule],
        declarations: [VisitorLocationDeleteDialogComponent]
      })
        .overrideTemplate(VisitorLocationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VisitorLocationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VisitorLocationService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
