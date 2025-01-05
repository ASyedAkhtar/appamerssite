import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { TopbarComponent } from './topbar/topbar.component';
import { StageComponent } from './stage/stage.component';
import { BottombarComponent } from './bottombar/bottombar.component';
import { ThemeService } from '../services/theme.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TopbarComponent, StageComponent, BottombarComponent, NgClass],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'amers.site';
  themeService: ThemeService = inject(ThemeService);
}
