import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVisitorLocation } from 'app/shared/model/visitor-location.model';
import { VisitorLocationService } from './visitor-location.service';

@Component({
  selector: 'jhi-visitor-location-delete-dialog',
  templateUrl: './visitor-location-delete-dialog.component.html'
})
export class VisitorLocationDeleteDialogComponent {
  visitorLocation: IVisitorLocation;

  constructor(
    protected visitorLocationService: VisitorLocationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.visitorLocationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'visitorLocationListModification',
        content: 'Deleted an visitorLocation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-visitor-location-delete-popup',
  template: ''
})
export class VisitorLocationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ visitorLocation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VisitorLocationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.visitorLocation = visitorLocation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/visitor-location', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/visitor-location', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
