import { HotelPreview } from '../hotel/hotelPreview';
import { Address } from '../address';

export class ReservationPreview {

    // Details about reservation
    id: number;
    created: Date = new Date();
    start: Date = new Date();
    end: Date = new Date();
    totalPrice: number;
    status: string;
    canCancel: boolean;

    // Details about hotel
    hotel: HotelPreview = new HotelPreview();

    // Details about room
    roomId: number;
    address: Address = new Address();
    roomNumber: number;
    // floorNumber: number;
    numberOfPeople: number;
}
