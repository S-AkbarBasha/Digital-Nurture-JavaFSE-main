import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

/** EX 7: Home page — part of Angular Routing exercise */
@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  exercises = [
    { path: '/welcome',      label: 'EX 2: Components',        icon: '📦', desc: 'Components, selectors, templates' },
    { path: '/data-binding', label: 'EX 3: Data Binding',      icon: '🔗', desc: 'Interpolation, property, event, two-way' },
    { path: '/directives',   label: 'EX 4: Directives',        icon: '🎛️', desc: '@if, @for, @switch, NgClass, NgStyle' },
    { path: '/pipes',        label: 'EX 5: Pipes',             icon: '🔄', desc: 'Built-in pipes + custom INR pipe' },
    { path: '/countries',    label: 'EX 6: Services & DI',     icon: '⚙️', desc: '@Injectable, constructor injection' },
    { path: '/about',        label: 'EX 7: Routing',           icon: '🗺️', desc: 'Router, routerLink, routerLinkActive' },
    { path: '/register',     label: 'EX 8: Template Forms',    icon: '📝', desc: 'ngModel, validation, form state' },
    { path: '/reactive',     label: 'EX 9: Reactive Forms',    icon: '⚡', desc: 'FormBuilder, FormGroup, Validators' },
    { path: '/users',        label: 'EX 10: HTTP Client',      icon: '🌐', desc: 'HttpClient, Observable, REST API' },
  ];
}
