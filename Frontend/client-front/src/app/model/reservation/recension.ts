export class Recension {
    id: number;
    isApproved: boolean;
    rating: number;
    comment: string;
    // reservation: Reservation = new Reservation();
    creationDate: Date = new Date();
    modificationDate: Date = new Date();

}
