import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IVisitorLocation, VisitorLocation } from 'app/shared/model/visitor-location.model';
import { VisitorLocationService } from './visitor-location.service';

@Component({
  selector: 'jhi-visitor-location-update',
  templateUrl: './visitor-location-update.component.html'
})
export class VisitorLocationUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ip: [],
    countryCode: [],
    countryName: [],
    region: [],
    city: [],
    latitude: [],
    longitude: [],
    zipcode: [],
    timezone: []
  });

  constructor(
    protected visitorLocationService: VisitorLocationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ visitorLocation }) => {
      this.updateForm(visitorLocation);
    });
  }

  updateForm(visitorLocation: IVisitorLocation) {
    this.editForm.patchValue({
      id: visitorLocation.id,
      ip: visitorLocation.ip,
      countryCode: visitorLocation.countryCode,
      countryName: visitorLocation.countryName,
      region: visitorLocation.region,
      city: visitorLocation.city,
      latitude: visitorLocation.latitude,
      longitude: visitorLocation.longitude,
      zipcode: visitorLocation.zipcode,
      timezone: visitorLocation.timezone
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const visitorLocation = this.createFromForm();
    if (visitorLocation.id !== undefined) {
      this.subscribeToSaveResponse(this.visitorLocationService.update(visitorLocation));
    } else {
      this.subscribeToSaveResponse(this.visitorLocationService.create(visitorLocation));
    }
  }

  private createFromForm(): IVisitorLocation {
    return {
      ...new VisitorLocation(),
      id: this.editForm.get(['id']).value,
      ip: this.editForm.get(['ip']).value,
      countryCode: this.editForm.get(['countryCode']).value,
      countryName: this.editForm.get(['countryName']).value,
      region: this.editForm.get(['region']).value,
      city: this.editForm.get(['city']).value,
      latitude: this.editForm.get(['latitude']).value,
      longitude: this.editForm.get(['longitude']).value,
      zipcode: this.editForm.get(['zipcode']).value,
      timezone: this.editForm.get(['timezone']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVisitorLocation>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
