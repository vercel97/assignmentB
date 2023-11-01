import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindPollComponent } from './find-poll.component';

describe('FindPollComponent', () => {
  let component: FindPollComponent;
  let fixture: ComponentFixture<FindPollComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FindPollComponent]
    });
    fixture = TestBed.createComponent(FindPollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
