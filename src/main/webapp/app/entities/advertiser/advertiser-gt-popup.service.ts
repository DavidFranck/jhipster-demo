import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { AdvertiserGt } from './advertiser-gt.model';
import { AdvertiserGtService } from './advertiser-gt.service';

@Injectable()
export class AdvertiserGtPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private advertiserService: AdvertiserGtService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.advertiserService.find(id).subscribe((advertiser) => {
                    advertiser.createDate = this.datePipe
                        .transform(advertiser.createDate, 'yyyy-MM-ddTHH:mm:ss');
                    advertiser.updateDate = this.datePipe
                        .transform(advertiser.updateDate, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.advertiserModalRef(component, advertiser);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.advertiserModalRef(component, new AdvertiserGt());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    advertiserModalRef(component: Component, advertiser: AdvertiserGt): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.advertiser = advertiser;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
