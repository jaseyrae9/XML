export class Recension {
    id: string;
    isApproved: boolean;
    rating: number;
    title: string;
    comment: string;
    username: string;
    creationDate: Date = new Date();
    reservationId: number;
}
