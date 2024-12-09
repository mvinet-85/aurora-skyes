import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { ToastComponent } from './shared/toast/toast.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ToastComponent, TranslateModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'aurora-skyes-front';

  constructor(private translate: TranslateService) {
    this.translate.addLangs(['en', 'fr', 'es']);
    this.translate.setDefaultLang('fr');
  }
}
