import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Authority } from './authority.model';
import { AuthorityPopupService } from './authority-popup.service';
import { AuthorityService } from './authority.service';
import { Menu, MenuService } from '../menu';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-authority-dialog',
    templateUrl: './authority-dialog.component.html'
})
export class AuthorityDialogComponent implements OnInit {

    authority: Authority;
    isSaving: boolean;

    menus: Menu[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private authorityService: AuthorityService,
        private menuService: MenuService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.menuService.query()
            .subscribe((res: ResponseWrapper) => { this.menus = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.authority.id !== undefined) {
            this.subscribeToSaveResponse(
                this.authorityService.update(this.authority));
        } else {
            this.subscribeToSaveResponse(
                this.authorityService.create(this.authority));
        }
    }

    private subscribeToSaveResponse(result: Observable<Authority>) {
        result.subscribe((res: Authority) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Authority) {
        this.eventManager.broadcast({ name: 'authorityListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackMenuById(index: number, item: Menu) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-authority-popup',
    template: ''
})
export class AuthorityPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private authorityPopupService: AuthorityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.authorityPopupService
                    .open(AuthorityDialogComponent as Component, params['id']);
            } else {
                this.authorityPopupService
                    .open(AuthorityDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
