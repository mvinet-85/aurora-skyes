import {Component, inject, OnInit} from '@angular/core';
import {flight} from '../../core/models/flight';
import {airport} from '../../core/models/airport';
import {DatePipe} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {SearchFormModel} from '../../core/models/form';
import {HeaderComponent} from '../../shared/header/header.component';
import {FlightService} from '../../core/services/flight/flight.service';

@Component({
  selector: 'app-home',
  imports: [
    DatePipe,
    ReactiveFormsModule,
    HeaderComponent
  ],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  public mockAirportList: airport[] = [
    {
      id: 1,
      nom: 'CDG',
      ville: 'Paris',
    },
    {
      id: 2,
      nom: 'JFK',
      ville: 'New-York',
    },
    {
      id: 3,
      nom: 'DTW',
      ville: 'Détroit',
    },
  ];

  public mockFlightList: flight[] = [
    {
      // flightNumber: '1',
      dateDepart: new Date('2024-02-10T10:00:00Z'),
      dateArrive: new Date('2024-02-10T14:00:00Z'),
      aeroportDepart: this.mockAirportList[0],
      aeroportArrivee: this.mockAirportList[1],
    },
    {
      // flightNumber: '2',
      dateDepart: new Date('2024-12-11T08:30:00Z'),
      dateArrive: new Date('2024-12-11T12:30:00Z'),
      aeroportDepart: this.mockAirportList[1],
      aeroportArrivee: this.mockAirportList[0],
    },
    {
      // flightNumber: '3',
      dateDepart: new Date('2024-12-12T16:45:00Z'),
      dateArrive: new Date('2024-12-12T18:15:00Z'),
      aeroportDepart: this.mockAirportList[1],
      aeroportArrivee: this.mockAirportList[2],
    },
  ];

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
  private readonly flightService: FlightService = inject(FlightService);

  ngOnInit(): void {
    this.airportList = this.mockAirportList;
    this.flightList = this.mockFlightList;
  }

  public onSearch(): void {
    console.log('flightSearchForm', this.flightSearchForm.value);
    console.log('flightList', this.flightList);

    this.error = '';

    if (this.flightSearchForm.valid) {
      const searchCriteria = this.flightSearchForm.value;
      const departureDate = searchCriteria.departureDate ? new Date(searchCriteria.departureDate) : null;
      const returnDate = searchCriteria.returnDate ? new Date(searchCriteria.returnDate) : null;

      if (departureDate && returnDate && departureDate > returnDate) {
        this.error = 'La date de retour doit être postérieure à la date de départ';
        return;
      }

      this.outboundFlights = this.flightList.filter(flight => {
        return (
          (!searchCriteria.departure || flight.aeroportDepart.nom === searchCriteria.departure.nom) &&
          (!searchCriteria.arrival || flight.aeroportArrivee.nom === searchCriteria.arrival.nom)
        );
      });

      this.returnFlights = this.flightList.filter(flight => {
        return (
          (!searchCriteria.arrival || flight.aeroportDepart.nom === searchCriteria.arrival.nom) &&
          (!searchCriteria.departure || flight.aeroportArrivee.nom === searchCriteria.departure.nom)
        );
      });

      this.search = true;
    }
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
}
