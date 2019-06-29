import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { Component, OnInit, Input } from '@angular/core';
import { removeDebugNodeFromIndex } from '@angular/core/src/debug/debug_node';

@Component({
  selector: 'app-review-display',
  templateUrl: './review-display.component.html',
  styleUrls: ['./review-display.component.css']
})
export class ReviewDisplayComponent implements OnInit {
  @Input() recensions: Recension[] = [];
  constructor() { }

  ngOnInit() {
  }

}
