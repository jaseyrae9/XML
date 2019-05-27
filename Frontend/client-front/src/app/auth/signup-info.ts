export class SignUpInfo {

    username: string;
    email: string;
    firstName: string;
    lastName: string;
    password: string;
    matchingPassword: string;

    constructor(username: string, email: string, firstName: string, lastName: string, password: string) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
  }
