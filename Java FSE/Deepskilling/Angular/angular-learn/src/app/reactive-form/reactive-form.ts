import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JsonPipe } from '@angular/common';

/**
 * EX 9: Reactive Forms
 * Uses FormBuilder, FormGroup, FormControl, and Validators programmatically.
 */
@Component({
  selector: 'app-reactive-form',
  imports: [ReactiveFormsModule, JsonPipe],
  templateUrl: './reactive-form.html',
  styleUrl: './reactive-form.css'
})
export class ReactiveForm implements OnInit {
  regForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.regForm = this.fb.group({
      name:     ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      email:    ['', [Validators.required, Validators.email]],
      mobile:   ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      city:     ['', Validators.required]
    });
  }

  // Getters for easy template access
  get name()     { return this.regForm.get('name'); }
  get email()    { return this.regForm.get('email'); }
  get mobile()   { return this.regForm.get('mobile'); }
  get password() { return this.regForm.get('password'); }
  get city()     { return this.regForm.get('city'); }

  cities = ['Mumbai', 'Delhi', 'Bangalore', 'Chennai', 'Hyderabad', 'Pune'];

  onSubmit(): void {
    if (this.regForm.valid) {
      this.submitted = true;
    } else {
      this.regForm.markAllAsTouched();
    }
  }

  onReset(): void {
    this.regForm.reset();
    this.submitted = false;
  }
}
