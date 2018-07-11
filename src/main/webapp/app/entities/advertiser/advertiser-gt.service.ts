import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { AdvertiserGt } from './advertiser-gt.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AdvertiserGtService {

    private resourceUrl = SERVER_API_URL + 'api/advertisers';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(advertiser: AdvertiserGt): Observable<AdvertiserGt> {
        const copy = this.convert(advertiser);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(advertiser: AdvertiserGt): Observable<AdvertiserGt> {
        const copy = this.convert(advertiser);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<AdvertiserGt> {
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

    private convert(advertiser: AdvertiserGt): AdvertiserGt {
        const copy: AdvertiserGt = Object.assign({}, advertiser);

        copy.createDate = this.dateUtils.toDate(advertiser.createDate);

        copy.updateDate = this.dateUtils.toDate(advertiser.updateDate);
        return copy;
    }
}
