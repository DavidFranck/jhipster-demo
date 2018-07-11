import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AdvertiserGtComponent } from './advertiser-gt.component';
import { AdvertiserGtDetailComponent } from './advertiser-gt-detail.component';
import { AdvertiserGtPopupComponent } from './advertiser-gt-dialog.component';
import { AdvertiserGtDeletePopupComponent } from './advertiser-gt-delete-dialog.component';

@Injectable()
export class AdvertiserGtResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const advertiserRoute: Routes = [
    {
        path: 'advertiser-gt',
        component: AdvertiserGtComponent,
        resolve: {
            'pagingParams': AdvertiserGtResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.advertiser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'advertiser-gt/:id',
        component: AdvertiserGtDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.advertiser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const advertiserPopupRoute: Routes = [
    {
        path: 'advertiser-gt-new',
        component: AdvertiserGtPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.advertiser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'advertiser-gt/:id/edit',
        component: AdvertiserGtPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.advertiser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'advertiser-gt/:id/delete',
        component: AdvertiserGtDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.advertiser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
