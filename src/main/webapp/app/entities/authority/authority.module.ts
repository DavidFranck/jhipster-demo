import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdxMgrSharedModule } from '../../shared';
import {
    AuthorityService,
    AuthorityPopupService,
    AuthorityComponent,
    AuthorityDetailComponent,
    AuthorityDialogComponent,
    AuthorityPopupComponent,
    AuthorityDeletePopupComponent,
    AuthorityDeleteDialogComponent,
    authorityRoute,
    authorityPopupRoute,
    AuthorityResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...authorityRoute,
    ...authorityPopupRoute,
];

@NgModule({
    imports: [
        AdxMgrSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuthorityComponent,
        AuthorityDetailComponent,
        AuthorityDialogComponent,
        AuthorityDeleteDialogComponent,
        AuthorityPopupComponent,
        AuthorityDeletePopupComponent,
    ],
    entryComponents: [
        AuthorityComponent,
        AuthorityDialogComponent,
        AuthorityPopupComponent,
        AuthorityDeleteDialogComponent,
        AuthorityDeletePopupComponent,
    ],
    providers: [
        AuthorityService,
        AuthorityPopupService,
        AuthorityResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdxMgrAuthorityModule {}
