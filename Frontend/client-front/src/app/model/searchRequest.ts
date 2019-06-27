export class SearchRequest {
    location: string;
    numberOfPeople: number;
    start: string;
    end: string;
    type: number;
    category: number;
    additionalServices: number[] = [];
    distanceFromLocation: number;
    cancelationDays: number;
       /* "location": "Novi Sad",
        "numberOfPeople": 2,
        "start":"2018-02-14",
        "end":"2018-02-18",
        "type": "",
        "category": "",
        "additionalServices": [1],
        "distanceFromLocation": "",
        "cancelationDays": ""*/

}
