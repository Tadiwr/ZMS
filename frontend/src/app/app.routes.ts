import { Routes } from '@angular/router';
import { ProvincePageComponent } from './province-page/province-page.component';

export const routes: Routes = [
  {
    path: "provinces", component: ProvincePageComponent
  },
  {
    path: "", component: ProvincePageComponent
  }
];
