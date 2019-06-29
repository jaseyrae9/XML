interface Reservation {
  id: number;
  roomId: number;
  dateFrom: Date;
  dateTo: Date;
  totalPrice;
  status: string;
  customer: Customer;
}
