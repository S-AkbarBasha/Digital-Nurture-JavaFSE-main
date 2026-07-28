import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

/** EX 7: Contact page */
@Component({
  selector: 'app-contact',
  imports: [RouterLink],
  template: `
    <div class="page-title">
      <span class="badge">EX 7 — Routing</span>
      <h1>Contact Page</h1>
      <p>Another routed page — navigate using the top nav bar</p>
    </div>
    <div class="card">
      <h2>📞 Contact Info</h2>
      <table>
        <tr><th>Field</th><th>Value</th></tr>
        <tr><td>Program</td><td>CTS Angular Hands-On</td></tr>
        <tr><td>Technology</td><td><span class="tag">Angular 19</span></td></tr>
        <tr><td>Language</td><td><span class="tag">TypeScript</span></td></tr>
      </table>
    </div>
    <a routerLink="/home" class="btn" style="text-decoration:none">← Back to Home</a>
  `
})
export class Contact {}
