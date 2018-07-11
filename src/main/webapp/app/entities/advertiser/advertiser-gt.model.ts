import { BaseEntity } from './../../shared';

export const enum DelFlag {
    'ON',
    'OFF'
}

export class AdvertiserGt implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public contacts?: string,
        public industryLevel1?: string,
        public industryLevel2?: string,
        public website?: string,
        public address?: string,
        public checkStatus?: string,
        public waxUid?: string,
        public orgCode?: string,
        public orgCodeCertificate?: string,
        public businessLicense?: string,
        public idCard?: string,
        public icp?: string,
        public taxesCertificate?: string,
        public networkBussinessCertificate?: string,
        public healthOrgCertificate?: string,
        public healthAdFile?: string,
        public gameAuthCertificate?: string,
        public gameVersionFile?: string,
        public celebrityEndorsement?: string,
        public taxRegistration?: string,
        public administrativ?: string,
        public authorization?: string,
        public remarks?: string,
        public createDate?: any,
        public updateDate?: any,
        public createBy?: number,
        public updateBy?: number,
        public delFlag?: DelFlag,
    ) {
    }
}
