export interface Task {
    id: number;
    title: string;
    description: string;
    created: Date;
    status: number;
    category: string;
    dueDate: Date;
}