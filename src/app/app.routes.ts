import { Routes } from '@angular/router';
import { Home } from './home/home';
import { About } from './about/about';
import { Contact } from './contact/contact';
import { Welcome } from './welcome/welcome';
import { DataBinding } from './data-binding/data-binding';
import { DirectivesDemo } from './directives-demo/directives-demo';
import { PipesDemo } from './pipes-demo/pipes-demo';
import { CountryList } from './country-list/country-list';
import { RegistrationForm } from './registration-form/registration-form';
import { ReactiveForm } from './reactive-form/reactive-form';
import { UserList } from './user-list/user-list';

export const routes: Routes = [
  { path: '',            redirectTo: 'home', pathMatch: 'full' },
  { path: 'home',        component: Home },             // EX 7 - Routing
  { path: 'about',       component: About },            // EX 7 - Routing
  { path: 'contact',     component: Contact },          // EX 7 - Routing
  { path: 'welcome',     component: Welcome },          // EX 2 - Components
  { path: 'data-binding',component: DataBinding },      // EX 3 - Data Binding
  { path: 'directives',  component: DirectivesDemo },   // EX 4 - Directives
  { path: 'pipes',       component: PipesDemo },        // EX 5 - Pipes
  { path: 'countries',   component: CountryList },      // EX 6 - Services
  { path: 'register',    component: RegistrationForm }, // EX 8 - Template Forms
  { path: 'reactive',    component: ReactiveForm },     // EX 9 - Reactive Forms
  { path: 'users',       component: UserList },         // EX 10 - HTTP Client
  { path: '**',          redirectTo: 'home' }
];
