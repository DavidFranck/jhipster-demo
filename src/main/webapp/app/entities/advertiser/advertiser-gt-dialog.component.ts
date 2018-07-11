import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AdvertiserGt } from './advertiser-gt.model';
import { AdvertiserGtPopupService } from './advertiser-gt-popup.service';
import { AdvertiserGtService } from './advertiser-gt.service';

@Component({
    selector: 'jhi-advertiser-gt-dialog',
    templateUrl: './advertiser-gt-dialog.component.html'
})
export class AdvertiserGtDialogComponent implements OnInit {

    advertiser: AdvertiserGt;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private advertiserService: AdvertiserGtService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.advertiser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.advertiserService.update(this.advertiser));
        } else {
            this.subscribeToSaveResponse(
                this.advertiserService.create(this.advertiser));
        }
    }

    private subscribeToSaveResponse(result: Observable<AdvertiserGt>) {
        result.subscribe((res: AdvertiserGt) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AdvertiserGt) {
        this.eventManager.broadcast({ name: 'advertiserListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-advertiser-gt-popup',
    template: ''
})
export class AdvertiserGtPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private advertiserPopupService: AdvertiserGtPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.advertiserPopupService
                    .open(AdvertiserGtDialogComponent as Component, params['id']);
            } else {
                this.advertiserPopupService
                    .open(AdvertiserGtDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
