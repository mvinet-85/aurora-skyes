import {Component, inject, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {HeaderComponent} from '../../shared/header/header.component';
import {ReservationStatService} from "../../core/services/statistique/reservation-stat.service";
import {Subscription} from "rxjs";
import {FlightService} from "../../core/services/flight/flight.service";
import {vol} from "../../core/models/vol";
import {ToastService} from "../../core/services/toast/toast.service";
import {BaseChartDirective} from "ng2-charts";
import {
  ArcElement,
  BarController,
  BarElement,
  CategoryScale,
  Chart,
  ChartConfiguration,
  ChartOptions,
  DoughnutController,
  Legend,
  LinearScale,
  LineController,
  LineElement,
  PieController,
  PointElement,
  Title,
  Tooltip
} from 'chart.js';

Chart.register(
  ArcElement,
  BarElement,
  BarController,
  DoughnutController,
  PieController,
  LineElement,
  PointElement,
  LineController,
  CategoryScale,
  LinearScale,
  Legend,
  Title,
  Tooltip
);

@Component({
  selector: 'app-reservation-state',
  imports: [
    HeaderComponent,
    BaseChartDirective
  ],
  templateUrl: './reservation-state.component.html',
  standalone: true,
  styleUrls: ['./reservation-state.component.scss']
})
export class ReservationStateComponent implements OnInit, OnDestroy {

  public reservationOK: number = 0;
  public reservationKO: number = 0;
  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;
  public flightList: vol[] = [];
  public chartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: [
      {
        label: 'Réservations OK',
        data: [],
        borderColor: '#007bff',
        backgroundColor: '#007bff',
        fill: false
      },
      {
        label: 'Réservations KO',
        data: [],
        borderColor: '#f44336',
        backgroundColor: '#f44336',
        fill: false
      }
    ]
  };
  public chartOptions: ChartOptions<'line'> = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: false,
      }
    },
    scales: {
      x: {
        title: {
          display: true,
          text: 'Temps'
        }
      },
      y: {
        title: {
          display: true,
          text: 'Nombre de Réservations'
        }
      }
    }
  };
  private readonly reservationStatService: ReservationStatService = inject(ReservationStatService);
  private readonly flightService: FlightService = inject(FlightService);
  private readonly toastService: ToastService = inject(ToastService);
  private readonly subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.getAllVols();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private getAllVols(): void {
    this.flightService.getAllVols().subscribe(
      (data) => {
        this.flightList = data;
        this.listenToAllFlights();
      },
      (error) => {
        console.error('Erreur lors de la récupération des vols', error);
        this.toastService.showToast('Erreur lors de la récupération des vols', 'error');
      }
    );
  }

  private listenToAllFlights(): void {
    const MAX_POINTS = 20;

    this.flightList.forEach(vol => {
      const subscription = this.reservationStatService.startListening(vol.id).subscribe(
        (event) => {
          const currentTime = new Date().toLocaleTimeString();

          if (event.event === 'ReservationOK') {
            this.reservationOK = event.data;
          } else if (event.event === 'ReservationError') {
            this.reservationKO = event.data;
          }

          // Ajout des nouvelles données
          this.chartData.labels?.push(currentTime);
          this.chartData.datasets[0].data.push(this.reservationOK);
          this.chartData.datasets[1].data.push(this.reservationKO);

          // Limitation des données affichées
          if (this.chartData.labels && this.chartData.labels.length > MAX_POINTS) {
            this.chartData.labels.shift();
            this.chartData.datasets[0].data.shift();
            this.chartData.datasets[1].data.shift();
          }

          this.chart?.update();
        },
        (error) => {
          console.error('Erreur dans le flux SSE :', error);
        }
      );

      this.subscriptions.push(subscription);
    });
  }


  private updateChartData(): void {
    this.chartData.datasets[0].data = [this.reservationOK, this.reservationKO];
  }
}
