import { Component, OnInit, Input } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Recension } from 'src/app/model/reservation/recension';

@Component({
  selector: 'app-review-details',
  templateUrl: './review-details.component.html',
  styleUrls: ['./review-details.component.css']
})
export class ReviewDetailsComponent implements OnInit {
  @Input() recensions: Recension[] = [];
  constructor(private datePipe: DatePipe) { }

  ngOnInit() {

  }

}
