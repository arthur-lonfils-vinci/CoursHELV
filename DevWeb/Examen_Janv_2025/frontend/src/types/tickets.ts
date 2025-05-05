
export interface Ticket {
    id: number;
    title: string;
    description: string;
    creator: string;
    creationDate: string;
  }
  
  export type NewTicket = Omit<Ticket, "id">;