import { Component } from '@angular/core';
import { DatePipe, CurrencyPipe, UpperCasePipe, LowerCasePipe, PercentPipe, JsonPipe, SlicePipe, DecimalPipe } from '@angular/common';
import { InrFormatPipe } from '../inr-format-pipe';

/**
 * EX 5: Pipes
 * Demonstrates built-in pipes and a custom INR format pipe.
 */
@Component({
  selector: 'app-pipes-demo',
  imports: [DatePipe, CurrencyPipe, UpperCasePipe, LowerCasePipe, PercentPipe, JsonPipe, SlicePipe, DecimalPipe, InrFormatPipe],
  templateUrl: './pipes-demo.html',
  styleUrl: './pipes-demo.css'
})
export class PipesDemo {
  name = 'angular hands-on exercises';
  salary = 75000;
  today = new Date();
  ratio = 0.7845;
  longText = 'The quick brown fox jumps over the lazy dog';
  pi = 3.14159265;
  employee = {
    name: 'John Doe',
    role: 'Angular Developer',
    experience: 3,
    skills: ['Angular', 'TypeScript', 'RxJS']
  };
}
