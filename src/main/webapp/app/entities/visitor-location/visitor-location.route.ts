import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VisitorLocation } from 'app/shared/model/visitor-location.model';
import { VisitorLocationService } from './visitor-location.service';
import { VisitorLocationComponent } from './visitor-location.component';
import { VisitorLocationDetailComponent } from './visitor-location-detail.component';
import { VisitorLocationUpdateComponent } from './visitor-location-update.component';
import { VisitorLocationDeletePopupComponent } from './visitor-location-delete-dialog.component';
import { IVisitorLocation } from 'app/shared/model/visitor-location.model';

@Injectable({ providedIn: 'root' })
export class VisitorLocationResolve implements Resolve<IVisitorLocation> {
  constructor(private service: VisitorLocationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVisitorLocation> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VisitorLocation>) => response.ok),
        map((visitorLocation: HttpResponse<VisitorLocation>) => visitorLocation.body)
      );
    }
    return of(new VisitorLocation());
  }
}

export const visitorLocationRoute: Routes = [
  {
    path: '',
    component: VisitorLocationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'VisitorLocations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VisitorLocationDetailComponent,
    resolve: {
      visitorLocation: VisitorLocationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'VisitorLocations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VisitorLocationUpdateComponent,
    resolve: {
      visitorLocation: VisitorLocationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'VisitorLocations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VisitorLocationUpdateComponent,
    resolve: {
      visitorLocation: VisitorLocationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'VisitorLocations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const visitorLocationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VisitorLocationDeletePopupComponent,
    resolve: {
      visitorLocation: VisitorLocationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'VisitorLocations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
