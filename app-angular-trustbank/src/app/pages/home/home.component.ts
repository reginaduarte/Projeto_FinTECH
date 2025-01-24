import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FooterComponent } from "../../shared/footer/footer.component";
import { FaqComponent } from "../../shared/faq/faq.component";
import { QuestionsComponent } from "../../shared/questions/questions.component";
import { FeaturesComponent } from "../../shared/features/features.component";
import { SectionComponent } from "../../shared/section/section.component";
import { CarouselComponent } from "../../shared/carousel/carousel.component";
import { NavbarComponent } from '../../shared/navbar/navbar.component';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FooterComponent, FaqComponent, QuestionsComponent, FeaturesComponent, SectionComponent, CarouselComponent, NavbarComponent], // Imports
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  showLogin = false;

  openLogin() {
    this.showLogin = true;
  }

  closeLogin() {
    this.showLogin = false;
  }
}
