import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

/** EX 7: About page — demonstrates Routing navigation */
@Component({
  selector: 'app-about',
  imports: [RouterLink],
  template: `
    <div class="page-title">
      <span class="badge">EX 7 — Routing</span>
      <h1>About Page</h1>
      <p>You navigated here using Angular Router</p>
    </div>
    <div class="card">
      <h2>🗺️ Angular Routing Concepts</h2>
      <table>
        <tr><th>Concept</th><th>Description</th></tr>
        <tr><td><span class="tag">Routes</span></td><td>Array of path-to-component mappings</td></tr>
        <tr><td><span class="tag">RouterModule</span></td><td>Provides routing directives and services</td></tr>
        <tr><td><span class="tag">router-outlet</span></td><td>Placeholder where routed component renders</td></tr>
        <tr><td><span class="tag">routerLink</span></td><td>Navigate without page reload</td></tr>
        <tr><td><span class="tag">routerLinkActive</span></td><td>Adds CSS class to active link</td></tr>
        <tr><td><span class="tag">ActivatedRoute</span></td><td>Reads URL params and query params</td></tr>
      </table>
    </div>
    <div class="card">
      <h2>🧭 Navigation</h2>
      <div style="display:flex; gap:12px; flex-wrap:wrap">
        <a routerLink="/home" class="btn" style="text-decoration:none">🏠 Home</a>
        <a routerLink="/contact" class="btn" style="text-decoration:none; background:rgba(99,102,241,0.3)">📞 Contact</a>
      </div>
    </div>
  `
})
export class About {}
