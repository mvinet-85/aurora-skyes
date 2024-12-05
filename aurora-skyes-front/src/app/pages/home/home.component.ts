import {Component, OnInit} from '@angular/core';
import {flight} from '../../core/models/flight';
import {airport} from '../../core/models/airport';
import {DatePipe} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {SearchFormModel} from '../../core/models/form';
import {HeaderComponent} from '../../shared/header/header.component';

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

  // TODO list à implementer avec les données du back
  public flightList: flight[] = [];
  public outboundFlights: flight[] = [];
  public returnFlights: flight[] = [];
  public airportList: airport[] = [];
  public search: boolean = false;

  public flightSearchForm: FormGroup<SearchFormModel> = new FormGroup<SearchFormModel>({
    tripType: new FormControl('aller-retour', [Validators.required]),
    departure: new FormControl(null, [Validators.required]),
    arrival: new FormControl(null, [Validators.required]),
    departureDate: new FormControl(null, [Validators.required]),
    returnDate: new FormControl(null, [Validators.required]),
    passengers: new FormControl(1, [Validators.required]),
    class: new FormControl('economy', [Validators.required])
  });

  ngOnInit(): void {
    this.airportList = this.mockAirportList;
  }

  public onSearch(): void {
    if (this.flightSearchForm.valid) {
      const searchCriteria = this.flightSearchForm.value;
      if (searchCriteria.departureDate) {
        searchCriteria.departureDate = new Date(searchCriteria.departureDate + 'T00:00:00Z');
      }
      if (searchCriteria.returnDate) {
        searchCriteria.returnDate = new Date(searchCriteria.returnDate + 'T00:00:00Z');
      }
      this.outboundFlights = this.mockFlightList.filter(flight => {
        return (searchCriteria.departure === null || flight.aeroportDepart === searchCriteria.departure) && (searchCriteria.arrival === null || flight.aeroportArrivee === searchCriteria.arrival) && (!searchCriteria.departureDate || flight.dateDepart >= searchCriteria.departureDate);
      });
      this.returnFlights = this.mockFlightList.filter(flight => {
        return (searchCriteria.arrival === null || flight.aeroportDepart === searchCriteria.arrival) && (searchCriteria.departure === null || flight.aeroportArrivee === searchCriteria.departure) && (!searchCriteria.returnDate || flight.dateArrive >= searchCriteria.returnDate);
      });
      this.search = true;
    }
  }
}
