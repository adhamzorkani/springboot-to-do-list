export class User {
    id: number;
    name: string;
    password: string;

    constructor(id: number, userName: string, password: string) {
        this.id = id;
        this.name = userName;
        this.password = password;
    }
}
