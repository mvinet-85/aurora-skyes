import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {HeaderComponent} from '../../shared/header/header.component';
import {ReservationStatService} from "../../core/services/statistique/reservation-stat.service";
import {Subscription} from "rxjs";
import {FlightService} from "../../core/services/flight/flight.service";
import {vol} from "../../core/models/vol";
import {ToastService} from "../../core/services/toast/toast.service";

@Component({
    selector: 'app-reservation-state',
    imports: [
        HeaderComponent
    ],
    templateUrl: './reservation-state.component.html',
    standalone: true,
    styleUrls: ['./reservation-state.component.scss']
})
export class ReservationStateComponent implements OnInit, OnDestroy {

    public reservationOK: number = 0;
    public reservationKO: number = 0;
    public flightList: vol[] = [];
    private reservationStatService: ReservationStatService = inject(ReservationStatService);
    private flightService: FlightService = inject(FlightService);
    private toastService: ToastService = inject(ToastService);
    private subscription: Subscription | null = null;

    ngOnInit(): void {
        this.getAllVols();
        this.flightList.forEach(value => this.subscription = this.reservationStatService.startListening(value.id).subscribe(
            (event) => {
                if (event.event === 'ReservationOK') {
                    this.reservationOK = event.data;
                } else if (event.event === 'ReservationError') {
                    console.error('Erreur reçue :', event.data);
                    this.reservationKO++;
                }
            },
            (error) => {
                console.error('Erreur dans le flux SSE :', error);
            }
        ));
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
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
}
