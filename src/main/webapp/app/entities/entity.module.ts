import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'visitor-location',
        loadChildren: () => import('./visitor-location/visitor-location.module').then(m => m.RaysRentalsVisitorLocationModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class RaysRentalsEntityModule {}
