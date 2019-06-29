import { ActivatedRoute } from '@angular/router';
import { AccommodationHTTPService } from './../../accommodation/service/accommodation-http.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-all-reservations-page',
  templateUrl: './all-reservations-page.component.html',
  styleUrls: ['./all-reservations-page.component.css']
})
export class AllReservationsPageComponent implements OnInit {
  @Input() room: Room = {} as Room;
  constructor(private accommodationService: AccommodationHTTPService, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.accommodationService.getRoom(id).subscribe(
      (room: Room) => {
        this.room = room;
      }
    );
  }

}
