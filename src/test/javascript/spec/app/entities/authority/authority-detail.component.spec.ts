/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { GtMgrTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AuthorityDetailComponent } from '../../../../../../main/webapp/app/entities/authority/authority-detail.component';
import { AuthorityService } from '../../../../../../main/webapp/app/entities/authority/authority.service';
import { Authority } from '../../../../../../main/webapp/app/entities/authority/authority.model';

describe('Component Tests', () => {

    describe('Authority Management Detail Component', () => {
        let comp: AuthorityDetailComponent;
        let fixture: ComponentFixture<AuthorityDetailComponent>;
        let service: AuthorityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GtMgrTestModule],
                declarations: [AuthorityDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AuthorityService,
                    JhiEventManager
                ]
            }).overrideTemplate(AuthorityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AuthorityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AuthorityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Authority(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.authority).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
