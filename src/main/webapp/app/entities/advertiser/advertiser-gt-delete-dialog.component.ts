import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AdvertiserGt } from './advertiser-gt.model';
import { AdvertiserGtPopupService } from './advertiser-gt-popup.service';
import { AdvertiserGtService } from './advertiser-gt.service';

@Component({
    selector: 'jhi-advertiser-gt-delete-dialog',
    templateUrl: './advertiser-gt-delete-dialog.component.html'
})
export class AdvertiserGtDeleteDialogComponent {

    advertiser: AdvertiserGt;

    constructor(
        private advertiserService: AdvertiserGtService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.advertiserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'advertiserListModification',
                content: 'Deleted an advertiser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-advertiser-gt-delete-popup',
    template: ''
})
export class AdvertiserGtDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private advertiserPopupService: AdvertiserGtPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.advertiserPopupService
                .open(AdvertiserGtDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
