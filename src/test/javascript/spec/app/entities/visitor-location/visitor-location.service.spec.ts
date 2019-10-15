import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { VisitorLocationService } from 'app/entities/visitor-location/visitor-location.service';
import { IVisitorLocation, VisitorLocation } from 'app/shared/model/visitor-location.model';

describe('Service Tests', () => {
  describe('VisitorLocation Service', () => {
    let injector: TestBed;
    let service: VisitorLocationService;
    let httpMock: HttpTestingController;
    let elemDefault: IVisitorLocation;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(VisitorLocationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new VisitorLocation('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a VisitorLocation', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new VisitorLocation(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a VisitorLocation', () => {
        const returnedFromService = Object.assign(
          {
            ip: 'BBBBBB',
            countryCode: 'BBBBBB',
            countryName: 'BBBBBB',
            region: 'BBBBBB',
            city: 'BBBBBB',
            latitude: 1,
            longitude: 1,
            zipcode: 'BBBBBB',
            timezone: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of VisitorLocation', () => {
        const returnedFromService = Object.assign(
          {
            ip: 'BBBBBB',
            countryCode: 'BBBBBB',
            countryName: 'BBBBBB',
            region: 'BBBBBB',
            city: 'BBBBBB',
            latitude: 1,
            longitude: 1,
            zipcode: 'BBBBBB',
            timezone: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a VisitorLocation', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
