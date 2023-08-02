export class User {
    id: number;
    username: string;
    password: string;
    active: boolean;
    authorities: [];

    constructor(id: number, userName: string, password: string, active: boolean, auth: []) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.active = active;
        this.authorities = auth;
    }
}
