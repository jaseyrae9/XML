interface Room{
  id: number;
  hotel: Hotel;
  address: Address;
  roomType: RoomType;
  roomCategory: RoomCategory;
  additionalService: AdditionalService[];
  floorNumber: number;
  roomNumber: number;
  defaultPrice: number;
  numberOfPeople: number;
  cancelationDays: number;
  description: string;
  totalRating: number;
  numberOfRatings: number;
}
