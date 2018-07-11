import { BaseEntity } from './../../shared';

export const enum Os {
    'IOS',
    'ANDROID'
}

export const enum ProductType {
    'APP_DOWNLOAD'
}

export const enum DelFlag {
    'ON',
    'OFF'
}

export class ProductGt implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public previewUrl?: string,
        public os?: Os,
        public type?: ProductType,
        public packetUrl?: string,
        public remarks?: string,
        public createDate?: any,
        public updateDate?: any,
        public createBy?: number,
        public updateBy?: number,
        public delFlag?: DelFlag,
    ) {
    }
}
