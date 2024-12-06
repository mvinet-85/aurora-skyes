import {Component} from '@angular/core';
import {Toast, ToastService} from '../../core/services/toast/toast.service';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-toast',
  imports: [
    NgClass
  ],
  templateUrl: './toast.component.html',
  standalone: true,
  styleUrl: './toast.component.scss'
})
export class ToastComponent {
  toasts: Toast[] = [];

  constructor(private toastService: ToastService) {
    this.toastService.getToasts().subscribe((toasts) => {
      this.toasts = toasts;
    });
  }

  removeToast(toast: Toast) {
    this.toastService.removeToast(toast);
  }
}
