import { LocationService } from './../../../location/service/location.service';
import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-room-form',
  templateUrl: './room-form.component.html',
  styleUrls: ['./room-form.component.css']
})
export class RoomFormComponent implements OnInit {
  hotel: Hotel;
  createRoomForm: FormGroup;

  roomTypes: RoomType[] = [];
  roomCategories: RoomCategory[] = [];
  additionalServices: AdditionalService[] = [];

  constructor(private formBuilder: FormBuilder, private accommodationHTTPService: AccommodationHTTPService,
              private locationService: LocationService) {
    this.createRoomForm = this.formBuilder.group({
      hotel : [''],
      roomType: ['', [Validators.required]],
      roomCategory: ['', [Validators.required]],
      roomNumber: ['', [Validators.required, Validators.min(0)]],
      floorNumber: ['', [Validators.required]],
      defaultPrice: ['', [Validators.required, Validators.min(0)]],
      numberOfPeople: ['', [Validators.required, Validators.min(0)]],
      cancelationDays: ['', [Validators.required, Validators.min(0)]],
      description: [''],
      additionalService: [''],
      address: this.formBuilder.group({
        street: ['', [Validators.required]],
        streetNumber: ['', [Validators.required]],
        city: ['', [Validators.required]],
        postalCode: ['', [Validators.required]],
        state: [''],
        country: ['', [Validators.required]],
        lat: ['', [Validators.required]],
        lng: ['', [Validators.required]]
      })
    });
  }

  ngOnInit() {
    this.accommodationHTTPService.getHotel().subscribe(
      (hotels) => {
        this.hotel = hotels.results[0];
        this.createRoomForm.patchValue({hotel: this.hotel.id});
        this.resetToHotelAddress();
      }
    );
    this.accommodationHTTPService.getRoomTypes().subscribe(
      (data) => {
        this.roomTypes = data.results;
      }
    );
    this.accommodationHTTPService.getRoomCategories().subscribe(
      (data) => {
        this.roomCategories = data.results;
      }
    );
    this.accommodationHTTPService.getAdditionalServices().subscribe(
      (data) => {
        this.additionalServices = data.results;
      }
    );
  }

  createRoom() {
    this.accommodationHTTPService.createRoom(this.createRoomForm.value).subscribe(
      (data) => {
        console.log(data);
      }
    )
  }

  resetToHotelAddress() {
    this.createRoomForm.patchValue({address: this.hotel.address});
  }

  geocode() {
    this.locationService.geocodeAddress(this.createRoomForm.value.address).subscribe(
      (data) => {
        const cLat = data.results[0].geometry.location.lat;
        const cLng = data.results[0].geometry.location.lng;
        this.createRoomForm.patchValue({address: {lng: cLng, lat: cLat}});
      },
      (error) => {
        alert('Error geocoding address.');
      }
    );
  }

}
