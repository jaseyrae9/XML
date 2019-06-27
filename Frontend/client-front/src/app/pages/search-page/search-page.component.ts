import { Component, OnInit } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RoomType } from 'src/app/model/room/roomType';
import { RoomCategory } from 'src/app/model/room/roomCategory';
import { SearchService } from 'src/app/services/search.service';
import { AdditionalService } from 'src/app/model/room/additionalService';
import { RoomPreview } from 'src/app/model/room/roomPreview';
import { SearchRequest } from 'src/app/model/searchRequest';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css',  '../../shared/css/inputField.css']
})
export class SearchPageComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig>;
  bsRangeValue: Date[];

  searchForm: FormGroup;
  filterForm: FormGroup;

  errorMessage = '';

  roomTypes: RoomType[] = [];
  roomCategories: RoomCategory[] = [];
  additionalServices: AdditionalService[] = [];

  rooms: RoomPreview[] = [];
  searchRequest: SearchRequest = new SearchRequest();

  constructor(public datePipe: DatePipe,
              private searchService: SearchService,
              private formBuilder: FormBuilder) {
    this.datePickerConfig = Object.assign({},
      {
        containerClass: 'theme-default',
        dateInputFormat: 'YYYY-MM-DD'
      });

    this.searchForm = this.formBuilder.group({
      location: ['', [Validators.required]],
      numberOfPeople: ['', [Validators.required, Validators.min(1)]],
      bsRangeValue: [this.bsRangeValue, [Validators.required]],
      roomType: [''],
      roomCategory: [''],
      additionalService: [''],
      distance: ['', [Validators.min(0)]],
      cancelationDays: ['', [Validators.min(0)]]
    });

  }

  ngOnInit() {
    this.loadRoomTypes();
    this.loadRoomCategories();
    this.loadAdditionalServices();
  }

  loadRoomTypes() {
    this.searchService.getTypes().subscribe(
      (data) => {
        this.roomTypes = data;
      }
    );
  }

  loadRoomCategories() {
    this.searchService.getCategories().subscribe(
      (data) => {
        this.roomCategories = data;
      }
    );
  }

  loadAdditionalServices() {
    this.searchService.getServices().subscribe(
      (data) => {
        this.additionalServices = data;
      }
    );
  }

  search() {
    const value = this.searchForm.value;

    this.searchRequest.location = value.location;
    this.searchRequest.numberOfPeople = value.numberOfPeople;
    this.searchRequest.start = this.datePipe.transform(value.bsRangeValue[0], 'yyyy-MM-dd');
    this.searchRequest.end = this.datePipe.transform(value.bsRangeValue[1], 'yyyy-MM-dd');
    this.searchRequest.type = value.roomType;
    this.searchRequest.category = value.roomCategory;
    if (value.additionalService === '') {
      this.searchRequest.additionalServices = [];
    } else {
      this.searchRequest.additionalServices = value.additionalService;
    }
    this.searchRequest.distanceFromLocation = value.distance;
    this.searchRequest.cancelationDays = value.cancelationDays;
    console.log('zahtev');
    console.log(this.searchRequest);
    this.searchService.search(this.searchRequest).subscribe(
      (data) => {
        this.rooms = data;
        console.log('odg');
        console.log(this.rooms);
      }
    );
  }

  cancelSearch() {
    this.rooms = [];
  }

}
