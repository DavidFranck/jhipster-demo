import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GtMgrSharedModule } from '../../shared';
import {
    ProductGtService,
    ProductGtPopupService,
    ProductGtComponent,
    ProductGtDetailComponent,
    ProductGtDialogComponent,
    ProductGtPopupComponent,
    ProductGtDeletePopupComponent,
    ProductGtDeleteDialogComponent,
    productRoute,
    productPopupRoute,
    ProductGtResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...productRoute,
    ...productPopupRoute,
];

@NgModule({
    imports: [
        GtMgrSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProductGtComponent,
        ProductGtDetailComponent,
        ProductGtDialogComponent,
        ProductGtDeleteDialogComponent,
        ProductGtPopupComponent,
        ProductGtDeletePopupComponent,
    ],
    entryComponents: [
        ProductGtComponent,
        ProductGtDialogComponent,
        ProductGtPopupComponent,
        ProductGtDeleteDialogComponent,
        ProductGtDeletePopupComponent,
    ],
    providers: [
        ProductGtService,
        ProductGtPopupService,
        ProductGtResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GtMgrProductGtModule {}
