import { HotelPreview } from '../hotel/hotelPreview';
import { Address } from '../address';
import { RoomType } from './roomType';
import { RoomCategory } from './roomCategory';
import { AdditionalService } from './additionalService';

export class RoomFull {
    id: number;
    hotel: HotelPreview = new HotelPreview();
    address: Address = new Address();
    type: RoomType = new RoomType();
    category: RoomCategory = new RoomCategory();
    additionalServices: AdditionalService[] = [];
    cancelationDays: number;
    numberOfPeople: number;
    roomNumber: number;
    floorNumber: number;
    description: string;
    defaultPrice: number; // double
    totalRating: number; // double
    numberOfRatings: number;
}
