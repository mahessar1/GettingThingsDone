export interface Projectlist {
    id?: number;
    listtype?: string;
    title: string;
    created?: string;
    tasklist?: any;
    description: string;
    priority: number;
    due: string;
    status: number;
}

export interface Actionlist {
    id?: number;
    title: string;
    created?: string;
    tasklist?: any;
    iconName: string;
    listtype?: string;
}