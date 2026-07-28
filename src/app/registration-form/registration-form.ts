import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

export interface RegistrationData {
  name: string;
  email: string;
  mobile: string;
  gender: string;
  city: string;
}

/**
 * EX 8: Template-Driven Forms
 * Uses ngModel, ngForm, built-in validators, and form state properties.
 */
@Component({
  selector: 'app-registration-form',
  imports: [FormsModule],
  templateUrl: './registration-form.html',
  styleUrl: './registration-form.css'
})
export class RegistrationForm {
  cities = ['Mumbai', 'Delhi', 'Bangalore', 'Chennai', 'Hyderabad', 'Pune', 'Kolkata'];
  submitted = false;
  formData: RegistrationData | null = null;

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.formData = { ...form.value };
      this.submitted = true;
    }
  }

  onReset(form: NgForm): void {
    form.resetForm();
    this.submitted = false;
    this.formData = null;
  }
}
