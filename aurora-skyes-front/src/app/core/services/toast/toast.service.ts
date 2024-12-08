import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

export interface Toast {
  id: number;
  message: string;
  type: 'success' | 'error' | 'info' | 'warning';
}

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  private toasts: Toast[] = [];
  private toastsSubject = new BehaviorSubject<Toast[]>(this.toasts);

  getToasts() {
    return this.toastsSubject.asObservable();
  }

  showToast(message: string, type: 'success' | 'error' | 'info') {
    const id = new Date().getTime();
    const toast: Toast = {id, message, type};
    this.toasts.push(toast);
    this.toastsSubject.next(this.toasts);

    setTimeout(() => this.removeToast(toast), 2000);
  }

  removeToast(toast: Toast) {
    this.toasts = this.toasts.filter(t => t.id !== toast.id);
    this.toastsSubject.next(this.toasts);
  }
}
