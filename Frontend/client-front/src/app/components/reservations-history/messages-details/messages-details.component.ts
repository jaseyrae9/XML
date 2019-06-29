import { Component, OnInit, Input } from '@angular/core';
import { Message } from 'src/app/model/reservation/message';

@Component({
  selector: 'app-messages-details',
  templateUrl: './messages-details.component.html',
  styleUrls: ['./messages-details.component.css']
})
export class MessagesDetailsComponent implements OnInit {
  @Input() message: Message = new Message();

  constructor() { }

  ngOnInit() {
  }


}
