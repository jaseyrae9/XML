import { Component, OnInit, Input } from '@angular/core';
import { Address } from 'src/app/model/address';

@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.css']
})
export class AddressDetailsComponent implements OnInit {
  @Input() address: Address;

  constructor() { }

  ngOnInit() {
  }

}
