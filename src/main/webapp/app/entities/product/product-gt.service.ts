import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { ProductGt } from './product-gt.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ProductGtService {

    private resourceUrl = SERVER_API_URL + 'api/products';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(product: ProductGt): Observable<ProductGt> {
        const copy = this.convert(product);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(product: ProductGt): Observable<ProductGt> {
        const copy = this.convert(product);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<ProductGt> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.createDate = this.dateUtils
            .convertDateTimeFromServer(entity.createDate);
        entity.updateDate = this.dateUtils
            .convertDateTimeFromServer(entity.updateDate);
    }

    private convert(product: ProductGt): ProductGt {
        const copy: ProductGt = Object.assign({}, product);

        copy.createDate = this.dateUtils.toDate(product.createDate);

        copy.updateDate = this.dateUtils.toDate(product.updateDate);
        return copy;
    }
}
