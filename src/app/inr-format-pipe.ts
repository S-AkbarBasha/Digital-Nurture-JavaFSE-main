import { Pipe, PipeTransform } from '@angular/core';

/**
 * EX 5: Custom Pipe
 * Formats a number as Indian Rupees with ₹ symbol and Indian locale formatting.
 * Usage: {{ value | inrFormat }}
 */
@Pipe({
  name: 'inrFormat'
})
export class InrFormatPipe implements PipeTransform {
  transform(value: number, decimals: number = 0): string {
    if (value === null || value === undefined) return '';
    return '₹ ' + value.toLocaleString('en-IN', {
      minimumFractionDigits: decimals,
      maximumFractionDigits: decimals
    });
  }
}
