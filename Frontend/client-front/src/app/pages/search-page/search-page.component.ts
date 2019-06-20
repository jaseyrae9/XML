import { Component, OnInit } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RoomType } from 'src/app/model/room/roomType';
import { RoomCategory } from 'src/app/model/room/roomCategory';
import { SearchService } from 'src/app/services/search.service';
import { AdditionalService } from 'src/app/model/room/additionalService';

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

  constructor(public datePipe: DatePipe,
              private searchService: SearchService,
              private formBuilder: FormBuilder) {
    this.datePickerConfig = Object.assign({},
      {
        containerClass: 'theme-default',
        dateInputFormat: 'YYYY-MM-DD'
      });

    this.searchForm = this.formBuilder.group({

    });

    this.filterForm = this.formBuilder.group({
      roomType: ['', [Validators.required]],
      roomCategory: ['', [Validators.required]],
      additionalService: [''],

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
        console.log('Tipovi');
        console.log(data);
      }
    );
  }

  loadRoomCategories() {
    this.searchService.getCategories().subscribe(
      (data) => {
        this.roomCategories = data;
        console.log('Kategorije');
        console.log(data);
      }
    );
  }

  loadAdditionalServices() {
    this.searchService.getServices().subscribe(
      (data) => {
        this.additionalServices = data;
        console.log('Dodatne usluge');
        console.log(data);
      }
    );
  }

}
