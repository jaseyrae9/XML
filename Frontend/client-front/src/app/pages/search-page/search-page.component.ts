import { Component, OnInit } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap';
import { DatePipe } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css',  '../../shared/css/inputField.css']
})
export class SearchPageComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig>;
  bsRangeValue: Date[];

  searchForm: FormGroup;
  errorMessage: String = '';

  constructor(public datePipe: DatePipe) {
    this.datePickerConfig = Object.assign({},
      {
        containerClass: 'theme-default',
        dateInputFormat: 'YYYY-MM-DD'
      });
  }

  ngOnInit() {
  }

}
