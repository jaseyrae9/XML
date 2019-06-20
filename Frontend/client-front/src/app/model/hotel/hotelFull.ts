import { Address } from '../address';
import { RoomPreview } from '../room/roomPreview';

export class HotelFull {
    id: number;
    name: string;
    address: Address = new Address();
    // sobe
    rooms: RoomPreview[] = [];
}
