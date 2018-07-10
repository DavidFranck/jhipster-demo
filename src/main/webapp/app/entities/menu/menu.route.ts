import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { MenuComponent } from './menu.component';
import { MenuDetailComponent } from './menu-detail.component';
import { MenuPopupComponent } from './menu-dialog.component';
import { MenuDeletePopupComponent } from './menu-delete-dialog.component';

@Injectable()
export class MenuResolvePagingParams implements Resolve<any> {

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

export const menuRoute: Routes = [
    {
        path: 'menu',
        component: MenuComponent,
        resolve: {
            'pagingParams': MenuResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.menu.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'menu/:id',
        component: MenuDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.menu.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const menuPopupRoute: Routes = [
    {
        path: 'menu-new',
        component: MenuPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.menu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'menu/:id/edit',
        component: MenuPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.menu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'menu/:id/delete',
        component: MenuDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gtMgrApp.menu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
