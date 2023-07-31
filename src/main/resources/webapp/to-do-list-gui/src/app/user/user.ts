export class User {
    id: number;
    name: string;
    password: string;
    active: boolean;
    auth: [];

    constructor(id: number, userName: string, password: string, active: boolean, auth: []) {
        this.id = id;
        this.name = userName;
        this.password = password;
        this.active = active;
        this.auth = auth;
    }
}
