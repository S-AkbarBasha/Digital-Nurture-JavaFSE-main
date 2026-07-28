import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

/**
 * EX 3: Data Binding
 * Demonstrates all 4 types of Angular data binding:
 * 1. Interpolation {{ }}
 * 2. Property Binding [ ]
 * 3. Event Binding ( )
 * 4. Two-Way Binding [( )] using ngModel
 */
@Component({
  selector: 'app-data-binding',
  imports: [FormsModule],
  templateUrl: './data-binding.html',
  styleUrl: './data-binding.css'
})
export class DataBinding {
  // 1. Interpolation
  title = 'Angular Data Binding';
  currentDate = new Date();

  // 2. Property Binding
  imageUrl = 'https://angular.dev/assets/images/ng-logo.svg';
  isButtonDisabled = false;
  altText = 'Angular Logo';

  // 3. Event Binding
  clickCount = 0;
  message = 'Click the button!';

  // 4. Two-Way Binding
  name = '';
  email = '';

  onClick(): void {
    this.clickCount++;
    this.message = `Button clicked ${this.clickCount} time(s)!`;
  }

  toggleButton(): void {
    this.isButtonDisabled = !this.isButtonDisabled;
  }

  clearForm(): void {
    this.name = '';
    this.email = '';
  }
}
