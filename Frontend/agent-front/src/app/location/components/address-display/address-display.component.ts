import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-address-display',
  templateUrl: './address-display.component.html',
  styleUrls: ['./address-display.component.css']
})
export class AddressDisplayComponent implements OnInit {
  @Input() address: Address = {} as Address;

  constructor() { }

  ngOnInit() {
  }

}
