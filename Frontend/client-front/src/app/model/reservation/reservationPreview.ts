import { HotelPreview } from '../hotel/hotelPreview';
import { Address } from '../address';

export class ReservationPreview {

    // Details about reservation
    id: number;
    start: Date = new Date();
    end: Date = new Date();
    totalPrice: number;
    status: ReservationStatus;
    canCancel: boolean;

    // Details about hotel
    hotel: HotelPreview = new HotelPreview();

    // Details about room
    roomId: number;
    address: Address = new Address();
    roomNumber: number;
    floorNumber: number;
    numberOfPeople: number;
}
