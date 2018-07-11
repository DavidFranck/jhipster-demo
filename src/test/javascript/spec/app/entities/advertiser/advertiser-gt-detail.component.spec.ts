/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { GtMgrTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AdvertiserGtDetailComponent } from '../../../../../../main/webapp/app/entities/advertiser/advertiser-gt-detail.component';
import { AdvertiserGtService } from '../../../../../../main/webapp/app/entities/advertiser/advertiser-gt.service';
import { AdvertiserGt } from '../../../../../../main/webapp/app/entities/advertiser/advertiser-gt.model';

describe('Component Tests', () => {

    describe('AdvertiserGt Management Detail Component', () => {
        let comp: AdvertiserGtDetailComponent;
        let fixture: ComponentFixture<AdvertiserGtDetailComponent>;
        let service: AdvertiserGtService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GtMgrTestModule],
                declarations: [AdvertiserGtDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AdvertiserGtService,
                    JhiEventManager
                ]
            }).overrideTemplate(AdvertiserGtDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AdvertiserGtDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdvertiserGtService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AdvertiserGt(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.advertiser).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
