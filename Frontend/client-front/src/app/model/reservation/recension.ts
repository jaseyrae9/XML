export class Recension {
    id: number;
    isApproved: boolean;
    rating: number;
    title: string;
    comment: string;
    username: string;
    // reservation: Reservation = new Reservation();
    creationDate: Date = new Date();

}
