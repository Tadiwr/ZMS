import { Component } from '@angular/core';
import { Province, ProvincesService } from '../provinces.service';
import { ProvinceRowCardComponent } from "../province-row-card/province-row-card.component";

@Component({
  selector: 'app-province-page',
  standalone: true,
  imports: [ProvinceRowCardComponent],
  templateUrl: './province-page.component.html',
  styleUrl: './province-page.component.css'
})
export class ProvincePageComponent {

  constructor (private provincesService: ProvincesService) {}

  provinces: Province[] = [];

  ngOnInit(): void {
    const observable = this.provincesService.getAllProvinces();

    observable.subscribe((res) => {
      this.provinces = res as Province[];

      console.log(this.provinces);
    });
  }

}
