import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Menu } from './menu.model';
import { MenuPopupService } from './menu-popup.service';
import { MenuService } from './menu.service';
import { Authority, AuthorityService } from '../authority';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-menu-dialog',
    templateUrl: './menu-dialog.component.html'
})
export class MenuDialogComponent implements OnInit {

    menu: Menu;
    isSaving: boolean;

    menus: Menu[];

    authorities: Authority[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private menuService: MenuService,
        private authorityService: AuthorityService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.menuService.query()
            .subscribe((res: ResponseWrapper) => { this.menus = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.authorityService.query()
            .subscribe((res: ResponseWrapper) => { this.authorities = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.menu.id !== undefined) {
            this.subscribeToSaveResponse(
                this.menuService.update(this.menu));
        } else {
            this.subscribeToSaveResponse(
                this.menuService.create(this.menu));
        }
    }

    private subscribeToSaveResponse(result: Observable<Menu>) {
        result.subscribe((res: Menu) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Menu) {
        this.eventManager.broadcast({ name: 'menuListModification', content: 'OK'});
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

    trackAuthorityById(index: number, item: Authority) {
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
    selector: 'jhi-menu-popup',
    template: ''
})
export class MenuPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private menuPopupService: MenuPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.menuPopupService
                    .open(MenuDialogComponent as Component, params['id']);
            } else {
                this.menuPopupService
                    .open(MenuDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
