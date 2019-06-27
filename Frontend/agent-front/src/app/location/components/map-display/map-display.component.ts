import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-map-display',
  templateUrl: './map-display.component.html',
  styleUrls: ['./map-display.component.css']
})
export class MapDisplayComponent implements OnInit {
  @Input() address: Address = {} as Address;
  constructor() { }

  ngOnInit() {
  }

}
