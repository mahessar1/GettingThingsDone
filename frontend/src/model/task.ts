export interface Task {
    id?: number;
    title: string;
    description: string;
    created?: string;
    status: number;
    dueDate: string;
    listId?: any
}