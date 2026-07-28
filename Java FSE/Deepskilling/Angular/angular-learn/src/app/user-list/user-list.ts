import { Component, OnInit } from '@angular/core';
import { ApiService, User } from '../services/api';

/**
 * EX 10: HTTP Client
 * Uses ApiService to fetch users from REST API.
 */
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.html',
  styleUrl: './user-list.css'
})
export class UserList implements OnInit {
  users: User[] = [];
  loading = true;
  error = '';
  selectedUser: User | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.loading = true;
    this.error = '';
    this.apiService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load users: ' + err.message;
        this.loading = false;
      }
    });
  }

  selectUser(user: User): void {
    this.selectedUser = this.selectedUser?.id === user.id ? null : user;
  }
}
