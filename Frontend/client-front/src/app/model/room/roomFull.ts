import { HotelPreview } from '../hotel/hotelPreview';
import { Address } from '../address';
import { RoomType } from './roomType';
import { RoomCategory } from './roomCategory';

export class RoomFull {
    id: number;
    hotel: HotelPreview;
    address: Address;
    type: RoomType;
    category: RoomCategory;
    // private List<AdditionalService> additionalServices;
    // additionalServices: AdditionalService[];
    cancelationDays: number;
    numberOfPeople: number;
    roomNumber: number;
    floorNumber: number;
    description: string;
    totalRating: number; // double
    numberOfRatings: number;
}
