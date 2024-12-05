import {Component, OnInit} from '@angular/core';
import {flight} from '../../core/models/flight';
import {airport} from '../../core/models/airport';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [
    DatePipe
  ],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  public mockFlightList: flight[] = [
    {
      flightNumber: '1',
      departure: new Date('2024-12-10T10:00:00Z'),
      arrival: new Date('2024-12-10T14:00:00Z'),
      from: 'CDG',
      to: 'JFK',
    },
    {
      flightNumber: '2',
      departure: new Date('2024-12-11T08:30:00Z'),
      arrival: new Date('2024-12-11T12:30:00Z'),
      from: 'JFK',
      to: 'CDG',
    },
    {
      flightNumber: '3',
      departure: new Date('2024-12-12T16:45:00Z'),
      arrival: new Date('2024-12-12T18:15:00Z'),
      from: 'JFK',
      to: 'DTW',
    },
  ];

  public mockAirportList: airport[] = [
    {
      nom: 'CDG',
      ville: 'Paris',
    },
    {
      nom: 'JFK',
      ville: 'New-York',
    },
    {
      nom: 'DTW',
      ville: 'Détroit',
    },
  ];

  public flightList: flight[] = [];
  public airportList: airport[] = [];

  ngOnInit(): void {
    this.flightList = this.mockFlightList;
    this.airportList = this.mockAirportList;
  }
}
