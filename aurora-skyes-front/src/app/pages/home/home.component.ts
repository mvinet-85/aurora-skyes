import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {flight} from '../../core/models/flight';
import {airport} from '../../core/models/airport';
import {DatePipe} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {SearchFormModel} from '../../core/models/form';
import {HeaderComponent} from '../../shared/header/header.component';
import {FlightService} from '../../core/services/flight/flight.service';
import {ConfirmModalComponent} from '../../shared/confirm-modal/confirm-modal.component';
import {reservation} from '../../core/models/reservation';
import {utilisateur} from '../../core/models/user';
import {ReservationService} from '../../core/services/reservation/reservation.service';
import {AirportService} from "../../core/services/airport/airport.service";

@Component({
    selector: 'app-home',
    imports: [
        DatePipe,
        ReactiveFormsModule,
        HeaderComponent,
        ConfirmModalComponent
    ],
    templateUrl: './home.component.html',
    standalone: true,
    styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

    public currentUser: utilisateur = {
        nom: 'nom',
        email: 'mail@mail.fr',
        motDePasse: 'mdp'
    };

    public flightSearchForm: FormGroup<SearchFormModel> = new FormGroup<SearchFormModel>({
        tripType: new FormControl('aller-retour', [Validators.required]),
        departure: new FormControl(null, [Validators.required]),
        arrival: new FormControl(null, [Validators.required]),
        departureDate: new FormControl(null, [Validators.required]),
        returnDate: new FormControl(null, [Validators.required]),
        passengers: new FormControl(1, [Validators.required]),
        class: new FormControl('economy', [Validators.required])
    });

    public flightList: flight[] = [];
    public outboundFlights: flight[] = [];
    public returnFlights: flight[] = [];
    public airportList: airport[] = [];
    public search: boolean = false;
    public error: string = '';
    public selectedFlight: flight | undefined;

    @ViewChild(ConfirmModalComponent) confirmModal!: ConfirmModalComponent;
    private readonly flightService: FlightService = inject(FlightService);
    private readonly airportService: AirportService = inject(AirportService);
    private readonly reservationService: ReservationService = inject(ReservationService);

    ngOnInit(): void {
        this.getAllVols();
        this.getAllAirports();
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

            if (searchCriteria && searchCriteria.departure && searchCriteria.arrival && searchCriteria.departureDate && searchCriteria.returnDate) {

                const departureDate = new Date(searchCriteria.departureDate);
                const returnDate = new Date(searchCriteria.returnDate);

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
            }

            this.search = true;
        }
    }

    openConfirmDialog(): void {
        this.confirmModal.show();
    }

    handleConfirmation(isConfirmed: boolean): void {
        if (isConfirmed && this.flightSearchForm && this.flightSearchForm.controls.class.value && this.selectedFlight) {
            const reservation: reservation = {
                vol: this.selectedFlight,
                utilisateur: this.currentUser,
                prix: 100,
                classe: this.flightSearchForm.controls.class.value,
                siege: 'siege',
            }
            console.log('Vol réservé', reservation);
            // TODO à mettre une fois branché au back
            // this.saveReservation(reservation);
        }
    }

    saveReservation(reservation: reservation): void {
        this.reservationService.save(reservation).subscribe(
            response => {
                console.log('Réservation sauvegardée avec succès', response);
            },
            error => {
                console.error('Erreur lors de la sauvegarde de la réservation', error);
            }
        );
    }

    selectFlight(flight: flight): void {
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
            }
        );
    }

    private getAllAirports(): void {
        this.airportService.getAllAirports().subscribe(
            (data) => {
                this.airportList = data;
            },
            (error) => {
                console.error('Erreur lors de la récupération des vols', error);
            }
        );
    }
}
