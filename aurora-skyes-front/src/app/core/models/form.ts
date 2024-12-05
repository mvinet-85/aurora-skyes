import {FormControl} from '@angular/forms';
import {airport} from './airport';

export interface SearchFormModel {
  tripType: FormControl<string | null>,
  departure: FormControl<airport | null>,
  arrival: FormControl<airport | null>,
  departureDate: FormControl<Date | null>,
  returnDate: FormControl<Date | null>,
  passengers: FormControl<number | null>,
  class: FormControl<string | null>,
}
