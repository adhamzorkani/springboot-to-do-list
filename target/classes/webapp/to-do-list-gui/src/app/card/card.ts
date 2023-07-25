import { User } from "../user/user";

export class Card {
    id: number;
    title: string;
    description: string;
    category: string;
    status: number;
    priority: number;
    date: string;
    user: User;

    constructor(id: number, title: string, description: string, category: string, status: number, priority: number, date: string, user: User) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.priority = priority;
        this.date = date;
        this.user = user;
    }
}
