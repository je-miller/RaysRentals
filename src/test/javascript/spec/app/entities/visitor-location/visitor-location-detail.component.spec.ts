import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RaysRentalsTestModule } from '../../../test.module';
import { VisitorLocationDetailComponent } from 'app/entities/visitor-location/visitor-location-detail.component';
import { VisitorLocation } from 'app/shared/model/visitor-location.model';

describe('Component Tests', () => {
  describe('VisitorLocation Management Detail Component', () => {
    let comp: VisitorLocationDetailComponent;
    let fixture: ComponentFixture<VisitorLocationDetailComponent>;
    const route = ({ data: of({ visitorLocation: new VisitorLocation('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RaysRentalsTestModule],
        declarations: [VisitorLocationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(VisitorLocationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VisitorLocationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.visitorLocation).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
