import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVisitorLocation } from 'app/shared/model/visitor-location.model';

@Component({
  selector: 'jhi-visitor-location-detail',
  templateUrl: './visitor-location-detail.component.html'
})
export class VisitorLocationDetailComponent implements OnInit {
  visitorLocation: IVisitorLocation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ visitorLocation }) => {
      this.visitorLocation = visitorLocation;
    });
  }

  previousState() {
    window.history.back();
  }
}
