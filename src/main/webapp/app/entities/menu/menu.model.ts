import { BaseEntity } from './../../shared';

export class Menu implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public href?: string,
        public icon?: string,
        public target?: string,
        public isShow?: string,
        public permission?: string,
        public sort?: number,
        public menuId?: number,
        public children?: BaseEntity[],
        public authorities?: BaseEntity[],
    ) {
    }
}
