import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { DatePipe } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, Input } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker/public_api';
import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
  selector: 'app-price-table',
  templateUrl: './price-table.component.html',
  styleUrls: ['./price-table.component.css']
})
export class PriceTableComponent implements OnInit {
  @Input() roomId;
  @Input() defaultPrice;
  calendarPlugins = [dayGridPlugin];

  datePickerConfig: Partial<BsDatepickerConfig> = Object.assign({},
  {
      containerClass: 'theme-default',
      dateInputFormat: 'DD-MM-YYYY'
  });

  priceChangeForm: FormGroup;

  calendarPrices = {
    events: [],
    color: '#33cabb',
    textColor: 'white'
  };

  constructor(private formBuilder: FormBuilder, private accommodationHTTPService: AccommodationHTTPService, private datePipe: DatePipe) {
    this.priceChangeForm = this.formBuilder.group({
      price: ['', [Validators.required, Validators.min(0)]],
      date: ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  submitPrice() {
    const priceChangeRequest = {
      amount: this.priceChangeForm.value.price,
      dateFrom: this.datePipe.transform(this.priceChangeForm.value.date[0], 'yyyy-MM-dd'),
      dateTo: this.datePipe.transform(this.priceChangeForm.value.date[1], 'yyyy-MM-dd'),
      room: this.roomId
    };
    this.accommodationHTTPService.setPrice(priceChangeRequest).subscribe(
      (data) => {
        data.forEach(element => {
          const newPrice = {title: element.amount + '$', start: element.date};
          const event = this.calendarPrices.events.find(e => e.start === newPrice.start);
          if (event) {
            event.title = newPrice.title;
          } else {
            this.calendarPrices.events.push(newPrice);
          }
        });
        this.priceChangeForm.reset();
      },
      (error) => {
        alert(error);
      }
    );
  }

  onRender(event) {
    const from = this.datePipe.transform(event.view.activeStart, 'yyyy-MM-dd');
    const to = this.datePipe.transform(event.view.activeEnd, 'yyyy-MM-dd');
    this.accommodationHTTPService.getPrices(this.roomId, from, to).subscribe(
      (data) => {
        this.calendarPrices.events = [];
        // set days with prices
        data.results.forEach(element => {
          const newPrice = {title: element.amount + '$', start: element.date};
          this.calendarPrices.events.push(newPrice);
        });
        // set default days
        this.fillEmpty(event.view.activeStart, event.view.activeEnd);
      }
    );
  }

  fillEmpty(from: Date, to: Date) {
    while (from < to) {
      const price = this.calendarPrices.events.find(e => e.start === this.datePipe.transform(from, 'yyyy-MM-dd'));
      if (price === undefined) {
        this.calendarPrices.events.push({title: this.defaultPrice + '$', start: this.datePipe.transform(from, 'yyyy-MM-dd')});
      }
      from.setDate(from.getDate() + 1);
    }
  }

}
