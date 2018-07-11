import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AdvertiserGt } from './advertiser-gt.model';
import { AdvertiserGtService } from './advertiser-gt.service';

@Component({
    selector: 'jhi-advertiser-gt-detail',
    templateUrl: './advertiser-gt-detail.component.html'
})
export class AdvertiserGtDetailComponent implements OnInit, OnDestroy {

    advertiser: AdvertiserGt;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private advertiserService: AdvertiserGtService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAdvertisers();
    }

    load(id) {
        this.advertiserService.find(id).subscribe((advertiser) => {
            this.advertiser = advertiser;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAdvertisers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'advertiserListModification',
            (response) => this.load(this.advertiser.id)
        );
    }
}
