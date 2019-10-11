import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { RaysRentalsSharedModule } from 'app/shared/shared.module';

import { AuditsComponent } from './audits.component';

import { auditsRoute } from './audits.route';

@NgModule({
  imports: [RaysRentalsSharedModule, RouterModule.forChild([auditsRoute])],
  declarations: [AuditsComponent]
})
export class AuditsModule {}
