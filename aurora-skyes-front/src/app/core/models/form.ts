import {FormControl} from '@angular/forms';

export interface SearchFormModel {
  tripType: FormControl<string | null>,
  departure: FormControl<string | null>,
  arrival: FormControl<string | null>,
  departureDate: FormControl<Date | null>,
  returnDate: FormControl<Date | null>,
  passengers: FormControl<number | null>,
  class: FormControl<string | null>,
  currencyRate: FormControl<number | null>,
}
