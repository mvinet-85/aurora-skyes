import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {ToastComponent} from './shared/toast/toast.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ToastComponent],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'aurora-skyes-front';
}
