import { Subject } from 'rxjs';
import { AccommodationHTTPService } from './../../service/accommodation-http.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ReservationService } from 'src/app/reservation/service/reservation.service';

@Component({
  selector: 'app-upload-image-form',
  templateUrl: './upload-image-form.component.html',
  styleUrls: ['./upload-image-form.component.css']
})
export class UploadImageFormComponent implements OnInit {
  @Input() roomId;
  @Output() uploadEvent: EventEmitter<Image> = new EventEmitter();

  constructor(private formBuilder: FormBuilder, private accommodationHTTPService: AccommodationHTTPService) { }
  uploadImageForm: FormGroup;
  selectedImage: File = null;

  ngOnInit() {
    this.uploadImageForm = this.formBuilder.group({
      is_cover: [false],
      photo: ['', [Validators.required]]
    });
  }

  onFileChange(event) {
    this.selectedImage = event.target.files[0];
  }

  uploadImage() {
    const fd = new FormData();
    fd.append('room', this.roomId);
    fd.append('photo', this.selectedImage, this.selectedImage.name);
    fd.append('is_cover', this.uploadImageForm.value.is_cover);
    this.accommodationHTTPService.addImage(fd).subscribe(
      (data) => {
        this.uploadEvent.next(data);
        this.uploadImageForm.reset({is_cover: false});
      }
    );
  }
}
