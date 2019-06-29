import { Address } from '../address';
import { RoomType } from './roomType';
import { RoomCategory } from './roomCategory';
import { HotelPreview } from '../hotel/hotelPreview';

export class RoomPreview {

    id: number;
    address: Address;
    type: RoomType;
    category: RoomCategory;
    numberOfPeople: number;
    roomNumber: number;
    mainImage: any[];

    // for search
    hotel: HotelPreview;
    totalStayPrice: number;
    distance: number;

}
