/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AdxMgrTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MenuDetailComponent } from '../../../../../../main/webapp/app/entities/menu/menu-detail.component';
import { MenuService } from '../../../../../../main/webapp/app/entities/menu/menu.service';
import { Menu } from '../../../../../../main/webapp/app/entities/menu/menu.model';

describe('Component Tests', () => {

    describe('Menu Management Detail Component', () => {
        let comp: MenuDetailComponent;
        let fixture: ComponentFixture<MenuDetailComponent>;
        let service: MenuService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AdxMgrTestModule],
                declarations: [MenuDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MenuService,
                    JhiEventManager
                ]
            }).overrideTemplate(MenuDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MenuDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MenuService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Menu(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.menu).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
