import { Component, OnInit, Input } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import { RoomService } from 'src/app/services/hotel/room.service';
import { Price } from 'src/app/model/room/price';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-price-table',
  templateUrl: './price-table.component.html',
  styleUrls: ['./price-table.component.css']
})
export class PriceTableComponent implements OnInit {
  calendarPlugins = [dayGridPlugin];
  @Input() defaultPrice;
  @Input() roomId;

  prices: Price[] = [];

  calendarPrices = {
    events: [],
    color: '#33cabb',
    textColor: 'white'
  };
  constructor(private roomService: RoomService,
              private datePipe: DatePipe) { }

  ngOnInit() {
  }

  onRender(event) {
    const from = this.datePipe.transform(event.view.activeStart, 'MM/dd/yyyy');
    const to = this.datePipe.transform(event.view.activeEnd, 'MM/dd/yyyy');
    this.roomService.getPrices(this.roomId, from, to).subscribe(
      (data) => {
        this.calendarPrices.events = [];
        // set days with prices
        data.forEach(element => {
          const newPrice = {title: element.amount + '$', start: this.datePipe.transform(element.date, 'yyyy-MM-dd')};
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
