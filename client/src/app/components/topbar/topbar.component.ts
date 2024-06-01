import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ThemeService } from '../../services/theme.service';

@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './topbar.component.html',
  styleUrl: './topbar.component.scss'
})
export class TopbarComponent {
  title = `Amer's Site`;
  themeService: ThemeService = inject(ThemeService);
  toggleTheme() {
    this.themeService.updateTheme();
  }
}
