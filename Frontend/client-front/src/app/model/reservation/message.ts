export class Message {
    id: number;
    message: string;
    creationDate: Date = new Date();
    status: string; // TO_AGENT | TO_CUSTOMER
}