import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carousel',
  imports: [CommonModule],
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {
  currentIndex = 0;
  slides: HTMLElement[] = []; // Inicializando como um array vazio

  ngOnInit() {
    this.slides = Array.from(document.querySelectorAll('.carousel-slide'));
    this.autoSlide();
  }

  updateSlidePosition() {
    const track = document.querySelector('.carousel-track') as HTMLElement;
    track.style.transform = `translateX(-${this.currentIndex * 100}%)`;
  }

  nextSlide() {
    this.currentIndex = (this.currentIndex + 1) % this.slides.length;
    this.updateSlidePosition();
  }

  prevSlide() {
    this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
    this.updateSlidePosition();
  }

  goToSlide(index: number) {
    this.currentIndex = index;
    this.updateSlidePosition();
  }

  autoSlide() {
    setInterval(() => {
      this.nextSlide();
    }, 5000);
  }
}