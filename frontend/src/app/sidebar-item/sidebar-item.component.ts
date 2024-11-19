import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-sidebar-item',
  standalone: true,
  imports: [],
  templateUrl: './sidebar-item.component.html',
  styleUrl: './sidebar-item.component.css'
})
export class SidebarItemComponent {

  @Input()
  href: string = "";

  
}
