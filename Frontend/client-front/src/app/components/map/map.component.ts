import { Component, OnInit, Input } from '@angular/core';
import { Address } from 'src/app/model/address';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  @Input() address: Address = new Address();

  constructor() { }

  ngOnInit() {
  }

}
