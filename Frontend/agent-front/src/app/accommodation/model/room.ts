interface Room{
  id: number;
  hotel: Hotel;
  address: Address;
  roomType: RoomType;
  roomCategory: RoomCategory;
  additionalService: AdditionalService[];
  roomFloor: number;
  roomNumber: number;
  defaultPrice: number;
  numberOfPeople: number;
  cancelationDays: number;
  description: string;
  avg_rating: number;
  number_of_ratings: number;
  fotos: Image[];
}
