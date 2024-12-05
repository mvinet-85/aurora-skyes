import {ComponentFixture, TestBed} from '@angular/core/testing';
import {FormsModule} from '@angular/forms';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {LoginComponent} from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [FormsModule, HttpClientTestingModule],
    }).compileComponents();

    httpMock = TestBed.inject(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize utilisateur with empty fields', () => {
    expect(component.utilisateur).toEqual({
      nom: '',
      email: '',
      motDePasse: '',
    });
  });

  it('should call the API on form submit', () => {
    component.utilisateur = {
      nom: 'Jean',
      email: 'jean@example.com',
      motDePasse: 'password123',
    };

    component.onSubmit();

    const req = httpMock.expectOne('http://localhost:8080/api/utilisateur/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(component.utilisateur);

    req.flush({success: true});
  });
});
