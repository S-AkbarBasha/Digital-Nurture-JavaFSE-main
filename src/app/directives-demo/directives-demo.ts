import { Component } from '@angular/core';
import { NgClass, NgStyle } from '@angular/common';

/**
 * EX 4: Built-in Directives
 * Structural: @if, @for, @switch (Angular 17+ control flow)
 * Attribute:  NgClass, NgStyle
 */
@Component({
  selector: 'app-directives-demo',
  imports: [NgClass, NgStyle],
  templateUrl: './directives-demo.html',
  styleUrl: './directives-demo.css'
})
export class DirectivesDemo {
  // @if demo
  isLoggedIn = false;

  // @for demo
  countries = [
    { code: 'IN', name: 'India', flag: '🇮🇳' },
    { code: 'US', name: 'United States', flag: '🇺🇸' },
    { code: 'DE', name: 'Germany', flag: '🇩🇪' },
    { code: 'JP', name: 'Japan', flag: '🇯🇵' },
    { code: 'AU', name: 'Australia', flag: '🇦🇺' },
  ];

  // @switch demo
  status: 'active' | 'inactive' | 'pending' = 'active';
  statuses: Array<'active' | 'inactive' | 'pending'> = ['active', 'inactive', 'pending'];

  // NgClass demo
  isHighlighted = false;
  isBold = false;

  // NgStyle demo
  textColor = '#a5b4fc';
  fontSize = 16;

  toggleLogin(): void { this.isLoggedIn = !this.isLoggedIn; }
  changeStatus(s: 'active' | 'inactive' | 'pending'): void { this.status = s; }
  increaseFontSize(): void { if (this.fontSize < 30) this.fontSize += 2; }
  decreaseFontSize(): void { if (this.fontSize > 10) this.fontSize -= 2; }
}
