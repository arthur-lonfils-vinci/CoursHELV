export interface Book {
    id: number;
    title: string;
    author: string;
    creationDate: string;
    image?: string;
}

  
export type NewBook = Omit<Book, "id">;