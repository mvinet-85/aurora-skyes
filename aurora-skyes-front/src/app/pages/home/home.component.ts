import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {vol} from '../../core/models/vol';
import {aeroport} from '../../core/models/aeroport';
import {DatePipe, NgForOf} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {SearchFormModel} from '../../core/models/form';
import {HeaderComponent} from '../../shared/header/header.component';
import {FlightService} from '../../core/services/flight/flight.service';
import {ConfirmModalComponent} from '../../shared/confirm-modal/confirm-modal.component';
import {reservation} from '../../core/models/reservation';
import {utilisateur} from '../../core/models/utilisateur';
import {ReservationService} from '../../core/services/reservation/reservation.service';
import {AirportService} from "../../core/services/airport/airport.service";
import {AuthService} from "../../core/services/authentification/auth.service";
import {ToastService} from '../../core/services/toast/toast.service';
import {monnaie} from '../../core/models/monnaie';
import {MonnaieService} from '../../core/services/monnaie/monnaie.service';

@Component({
  selector: 'app-home',
  imports: [
    DatePipe,
    ReactiveFormsModule,
    HeaderComponent,
    ConfirmModalComponent,
    NgForOf
  ],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  public currentUser: utilisateur | null = {
    nom: '',
    motDePasse: '',
    email: '',
  };

  public flightSearchForm: FormGroup<SearchFormModel> = new FormGroup<SearchFormModel>({
    tripType: new FormControl('aller-retour', [Validators.required]),
    departure: new FormControl(null, [Validators.required]),
    arrival: new FormControl(null, [Validators.required]),
    departureDate: new FormControl(null, [Validators.required]),
    returnDate: new FormControl(null, [Validators.required]),
    passengers: new FormControl(1, [Validators.required]),
    class: new FormControl('economy', [Validators.required]),
    currency: new FormControl('EUR', [Validators.required]),
  });

  public flightList: vol[] = [];
  public outboundFlights: vol[] = [];
  public returnFlights: vol[] = [];
  public airportList: aeroport[] = [];
  public monnaieList: monnaie[] = [];
  public search: boolean = false;
  public error: string = '';
  public selectedFlight: vol | undefined;

  @ViewChild(ConfirmModalComponent) confirmModal!: ConfirmModalComponent;
  private readonly flightService: FlightService = inject(FlightService);
  private readonly airportService: AirportService = inject(AirportService);
  private readonly reservationService: ReservationService = inject(ReservationService);
  private readonly authService: AuthService = inject(AuthService);
  private readonly toastService: ToastService = inject(ToastService);
  private readonly monnaieService: MonnaieService = inject(MonnaieService);

  ngOnInit(): void {
    this.getAllVols();
    this.getAllAirports();
    this.getAllCurrencies();
    this.currentUser = this.authService.getUser();
  }

  public onSearch(): void {
    this.error = '';

    if (this.flightSearchForm.valid) {
      const searchCriteria = this.flightSearchForm.value;
      const departureDate = searchCriteria.departureDate ? new Date(searchCriteria.departureDate) : null;
      const returnDate = searchCriteria.returnDate ? new Date(searchCriteria.returnDate) : null;

      if (departureDate && returnDate && departureDate > returnDate) {
        this.error = 'La date de retour doit être postérieure à la date de départ';
        return;
      }

      if (searchCriteria.arrival == searchCriteria.departure) {
        this.error = 'L\'aéroport de départ et d\'arrivée doivent être differents';
        return;
      }

      if (searchCriteria.departure && searchCriteria.arrival && searchCriteria.departureDate && searchCriteria.returnDate) {

        this.outboundFlights = this.flightList.filter(flight => {
          if (flight.aeroportDepart && flight.aeroportArrivee) {
            return (
              flight.aeroportArrivee.nom === searchCriteria.arrival &&
              flight.aeroportDepart.nom === searchCriteria.departure
            );
          }
          return false;
        });

        this.returnFlights = this.flightList.filter(flight => {
          if (flight.aeroportDepart && flight.aeroportArrivee) {
            return (
              flight.aeroportArrivee.nom === searchCriteria.departure &&
              flight.aeroportDepart.nom === searchCriteria.arrival
            );
          }
          return false;
        });

        if (this.flightSearchForm.controls.currency.value != null && this.flightSearchForm.controls.currency.value !== 'EUR') {
          const defaultMonnaie: monnaie = {nom: 'EUR', taux: 1};
          const monnaie: monnaie = this.monnaieList.find(monnaie => monnaie.nom == this.flightSearchForm.controls.currency.value) ?? defaultMonnaie;
          this.outboundFlights.forEach(flight => {
            flight.prix = this.monnaieService.getPrice(monnaie.taux, flight.prix);
          });

          this.returnFlights.forEach(flight => {
            flight.prix = this.monnaieService.getPrice(monnaie.taux, flight.prix);
          });
        }
      }

      this.search = true;
    }
  }

  openConfirmDialog(): void {
    this.confirmModal.show();
  }

  handleConfirmation(isConfirmed: boolean): void {
    if (
      isConfirmed &&
      this.flightSearchForm.controls.class &&
      this.selectedFlight && this.currentUser?.id &&
      this.flightSearchForm.controls.class.value
    ) {

      const reservation: reservation = {
        volId: this.selectedFlight.id,
        userId: this.currentUser?.id,
        classe: this.flightSearchForm.controls.class.value,
        siege: 'siege',
      }
      this.saveReservation(reservation);
    }
  }

  saveReservation(reservation: reservation): void {
    this.reservationService.save(reservation).subscribe(
      response => {
        this.toastService.showToast('Résevration enregistrée', 'success')
      },
      error => {
        console.error('Erreur lors de la sauvegarde de la réservation', error);
        this.toastService.showToast('Erreur lors de la sauvegarde de la réservation', 'error')
      }
    );
  }

  selectFlight(flight: vol): void {
    this.selectedFlight = flight;
    this.openConfirmDialog();
  }

  private getAllVols(): void {
    this.flightService.getAllVols().subscribe(
      (data) => {
        this.flightList = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des vols', error);
        this.toastService.showToast('Erreur lors de la récupération des vols', 'error')
      }
    );
  }

  private getAllAirports(): void {
    this.airportService.getAllAirports().subscribe(
      (data: aeroport[]) => {
        this.airportList = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des aéroports', error);
        this.toastService.showToast('Erreur lors de la récupération des aéroports', 'error')
      }
    );
  }

  private getAllCurrencies(): void {
    this.monnaieService.getAllCurrencies().subscribe(
      (data: monnaie[]) => {
        this.monnaieList = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des monnaies', error);
        this.toastService.showToast('Erreur lors de la récupération des monnaies', 'error')
      }
    );
  }
}
