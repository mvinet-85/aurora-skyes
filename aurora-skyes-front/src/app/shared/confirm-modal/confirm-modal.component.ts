import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-confirm-modal',
  imports: [],
  templateUrl: './confirm-modal.component.html',
  standalone: true,
  styleUrl: './confirm-modal.component.scss'
})
export class ConfirmModalComponent {
  @Output() confirm = new EventEmitter<boolean>();
  isVisible = false;

  show() {
    this.isVisible = true;
  }

  hide() {
    this.isVisible = false;
  }

  onConfirm() {
    this.confirm.emit(true);
    this.hide();
  }

  onCancel() {
    this.confirm.emit(false);
    this.hide();
  }
}
