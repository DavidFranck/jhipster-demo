import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProductGtComponent } from './product-gt.component';
import { ProductGtDetailComponent } from './product-gt-detail.component';
import { ProductGtPopupComponent } from './product-gt-dialog.component';
import { ProductGtDeletePopupComponent } from './product-gt-delete-dialog.component';

@Injectable()
export class ProductGtResolvePagingParams implements Resolve<any> {

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

export const productRoute: Routes = [
    {
        path: 'product-gt',
        component: ProductGtComponent,
        resolve: {
            'pagingParams': ProductGtResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'product-gt/:id',
        component: ProductGtDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productPopupRoute: Routes = [
    {
        path: 'product-gt-new',
        component: ProductGtPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.product.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-gt/:id/edit',
        component: ProductGtPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.product.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-gt/:id/delete',
        component: ProductGtDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.product.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
