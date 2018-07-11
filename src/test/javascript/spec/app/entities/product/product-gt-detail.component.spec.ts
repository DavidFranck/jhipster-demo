/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { GtMgrTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProductGtDetailComponent } from '../../../../../../main/webapp/app/entities/product/product-gt-detail.component';
import { ProductGtService } from '../../../../../../main/webapp/app/entities/product/product-gt.service';
import { ProductGt } from '../../../../../../main/webapp/app/entities/product/product-gt.model';

describe('Component Tests', () => {

    describe('ProductGt Management Detail Component', () => {
        let comp: ProductGtDetailComponent;
        let fixture: ComponentFixture<ProductGtDetailComponent>;
        let service: ProductGtService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GtMgrTestModule],
                declarations: [ProductGtDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProductGtService,
                    JhiEventManager
                ]
            }).overrideTemplate(ProductGtDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProductGtDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductGtService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ProductGt(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.product).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
