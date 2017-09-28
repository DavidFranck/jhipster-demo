import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdxMgrSharedModule } from '../../shared';
import {
    MenuService,
    MenuPopupService,
    MenuComponent,
    MenuDetailComponent,
    MenuDialogComponent,
    MenuPopupComponent,
    MenuDeletePopupComponent,
    MenuDeleteDialogComponent,
    menuRoute,
    menuPopupRoute,
    MenuResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...menuRoute,
    ...menuPopupRoute,
];

@NgModule({
    imports: [
        AdxMgrSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MenuComponent,
        MenuDetailComponent,
        MenuDialogComponent,
        MenuDeleteDialogComponent,
        MenuPopupComponent,
        MenuDeletePopupComponent,
    ],
    entryComponents: [
        MenuComponent,
        MenuDialogComponent,
        MenuPopupComponent,
        MenuDeleteDialogComponent,
        MenuDeletePopupComponent,
    ],
    providers: [
        MenuService,
        MenuPopupService,
        MenuResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdxMgrMenuModule {}
