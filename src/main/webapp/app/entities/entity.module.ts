import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AdxMgrMenuModule } from './menu/menu.module';
import { AdxMgrAuthorityModule } from './authority/authority.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AdxMgrMenuModule,
        AdxMgrAuthorityModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdxMgrEntityModule {}
