import { Injectable } from '@angular/core';

export interface Country {
  code: string;
  name: string;
  capital: string;
  population: string;
  flag: string;
}

/**
 * EX 6: CountryService
 * Provides country data as a singleton injectable service.
 * Injected via constructor in CountryList component.
 */
@Injectable({
  providedIn: 'root'
})
export class CountryService {

  private countries: Country[] = [
    { code: 'IN', name: 'India',          capital: 'New Delhi',  population: '1.4B', flag: '🇮🇳' },
    { code: 'US', name: 'United States',  capital: 'Washington', population: '335M', flag: '🇺🇸' },
    { code: 'DE', name: 'Germany',        capital: 'Berlin',     population: '84M',  flag: '🇩🇪' },
    { code: 'JP', name: 'Japan',          capital: 'Tokyo',      population: '125M', flag: '🇯🇵' },
    { code: 'AU', name: 'Australia',      capital: 'Canberra',   population: '26M',  flag: '🇦🇺' },
    { code: 'FR', name: 'France',         capital: 'Paris',      population: '68M',  flag: '🇫🇷' },
    { code: 'GB', name: 'United Kingdom', capital: 'London',     population: '67M',  flag: '🇬🇧' },
    { code: 'CA', name: 'Canada',         capital: 'Ottawa',     population: '39M',  flag: '🇨🇦' },
  ];

  /** Returns all countries */
  getCountries(): Country[] {
    return this.countries;
  }

  /** Case-insensitive search by country code */
  getCountryByCode(code: string): Country | undefined {
    return this.countries.find(c =>
      c.code.toLowerCase() === code.toLowerCase()
    );
  }

  /** Search by partial name */
  searchByName(query: string): Country[] {
    return this.countries.filter(c =>
      c.name.toLowerCase().includes(query.toLowerCase())
    );
  }
}
