import { Component } from '@angular/core';

/**
 * EX 2: Angular Components
 * Demonstrates @Component decorator, selector, templateUrl, and class properties.
 */
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.html',
  styleUrl: './welcome.css'
})
export class Welcome {
  title = 'Welcome to Angular!';
  subtitle = 'This is EX 2 — Angular Components';
  author = 'CTS Angular Hands-On';
  version = 'Angular 19';
  features = [
    'Standalone Components',
    'Signal-based reactivity',
    'Built-in Control Flow (@if, @for)',
    'TypeScript support',
    'Angular CLI'
  ];
}
