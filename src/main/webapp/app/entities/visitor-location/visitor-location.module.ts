import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RaysRentalsSharedModule } from 'app/shared/shared.module';
import { VisitorLocationComponent } from './visitor-location.component';
import { VisitorLocationDetailComponent } from './visitor-location-detail.component';
import { VisitorLocationUpdateComponent } from './visitor-location-update.component';
import { VisitorLocationDeletePopupComponent, VisitorLocationDeleteDialogComponent } from './visitor-location-delete-dialog.component';
import { visitorLocationRoute, visitorLocationPopupRoute } from './visitor-location.route';

const ENTITY_STATES = [...visitorLocationRoute, ...visitorLocationPopupRoute];

@NgModule({
  imports: [RaysRentalsSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VisitorLocationComponent,
    VisitorLocationDetailComponent,
    VisitorLocationUpdateComponent,
    VisitorLocationDeleteDialogComponent,
    VisitorLocationDeletePopupComponent
  ],
  entryComponents: [VisitorLocationDeleteDialogComponent]
})
export class RaysRentalsVisitorLocationModule {}
