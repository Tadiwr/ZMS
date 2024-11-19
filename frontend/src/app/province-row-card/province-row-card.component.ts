import { Component, Input } from '@angular/core';
import { Province } from '../provinces.service';

@Component({
  selector: 'app-province-row-card',
  standalone: true,
  imports: [],
  templateUrl: './province-row-card.component.html',
  styleUrl: './province-row-card.component.css'
})
export class ProvinceRowCardComponent {

  @Input()
  name!: String;

  @Input()
  provinceId!: number;

}
