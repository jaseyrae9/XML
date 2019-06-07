import { Address } from '../address';
import { RoomType } from './roomType';
import { RoomCategory } from './roomCategory';

export class RoomPreview {

    id: number;
    address: Address;
    type: RoomType;
    category: RoomCategory;
    numberOfPeople: number;
    roomNumber: number;
}
