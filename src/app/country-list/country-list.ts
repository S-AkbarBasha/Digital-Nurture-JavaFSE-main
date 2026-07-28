import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CountryService, Country } from '../services/country';

/**
 * EX 6: Services & Dependency Injection
 * CountryService is injected via constructor.
 */
@Component({
  selector: 'app-country-list',
  imports: [FormsModule],
  templateUrl: './country-list.html',
  styleUrl: './country-list.css'
})
export class CountryList implements OnInit {
  countries: Country[] = [];
  filteredCountries: Country[] = [];
  searchQuery = '';
  selectedCountry: Country | null = null;
  searchCode = '';
  codeResult: Country | undefined;

  // Constructor Injection — Angular provides CountryService automatically
  constructor(private countryService: CountryService) {}

  ngOnInit(): void {
    this.countries = this.countryService.getCountries();
    this.filteredCountries = this.countries;
  }

  onSearch(): void {
    this.filteredCountries = this.countryService.searchByName(this.searchQuery);
  }

  onSearchByCode(): void {
    this.codeResult = this.countryService.getCountryByCode(this.searchCode);
  }

  selectCountry(c: Country): void {
    this.selectedCountry = this.selectedCountry?.code === c.code ? null : c;
  }
}
