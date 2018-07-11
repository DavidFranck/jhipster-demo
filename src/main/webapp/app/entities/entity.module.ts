import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GtMgrMenuModule } from './menu/menu.module';
import { GtMgrAuthorityModule } from './authority/authority.module';
import { GtMgrAdvertiserGtModule } from './advertiser/advertiser-gt.module';
import { GtMgrProductGtModule } from './product/product-gt.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        GtMgrMenuModule,
        GtMgrAuthorityModule,
        GtMgrAdvertiserGtModule,
        GtMgrProductGtModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GtMgrEntityModule {}
