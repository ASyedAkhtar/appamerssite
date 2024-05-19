import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  themeSignal = signal<string>('dark');

  constructor() { }

  setTheme(theme: string) {
    this.themeSignal.set(theme);
  }

  updateTheme() {
    this.themeSignal.update(theme => theme === 'dark' ? 'light' : 'dark');
  }
}
