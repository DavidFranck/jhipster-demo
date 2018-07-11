import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GtMgrSharedModule } from '../../shared';
import {
    AdvertiserGtService,
    AdvertiserGtPopupService,
    AdvertiserGtComponent,
    AdvertiserGtDetailComponent,
    AdvertiserGtDialogComponent,
    AdvertiserGtPopupComponent,
    AdvertiserGtDeletePopupComponent,
    AdvertiserGtDeleteDialogComponent,
    advertiserRoute,
    advertiserPopupRoute,
    AdvertiserGtResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...advertiserRoute,
    ...advertiserPopupRoute,
];

@NgModule({
    imports: [
        GtMgrSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AdvertiserGtComponent,
        AdvertiserGtDetailComponent,
        AdvertiserGtDialogComponent,
        AdvertiserGtDeleteDialogComponent,
        AdvertiserGtPopupComponent,
        AdvertiserGtDeletePopupComponent,
    ],
    entryComponents: [
        AdvertiserGtComponent,
        AdvertiserGtDialogComponent,
        AdvertiserGtPopupComponent,
        AdvertiserGtDeleteDialogComponent,
        AdvertiserGtDeletePopupComponent,
    ],
    providers: [
        AdvertiserGtService,
        AdvertiserGtPopupService,
        AdvertiserGtResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GtMgrAdvertiserGtModule {}
