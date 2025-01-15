
import path from "node:path";
import { Ticket, NewTicket } from "../types";
import { parse, serialize } from "../utils/json";


const jsonDbPath = path.join(__dirname, "/../data/tickets.json");

const defaultTickets: Ticket[] = [
  {
    id: 1,
    title: "ticket1",
    description: "projet de fin de formation",
    creator: "Joseph Perdu",
    creationDate: "2021-10-01",
  },
];



//CHANGE
export function readAllTickets(order: string | undefined): Ticket[] {
  const orderByTitle = order && order.includes("title") ? order : undefined;

  let orderedMenu: Ticket[] = [];
  const tickets = parse(jsonDbPath, defaultTickets);
  if (orderByTitle)
    orderedMenu = [...tickets].sort((a, b) => a.title.localeCompare(b.title));

  if (orderByTitle === "-title") orderedMenu = orderedMenu.reverse();

  return orderedMenu.length === 0 ? tickets : orderedMenu;
}

//CHANGE
export function readTicketById(id: number): Ticket | undefined {
  const tickets = parse(jsonDbPath, defaultTickets);
  return tickets.find((ticket) => ticket.id === id);
}


export function isTitlePresent(title: string): Ticket | undefined {
  const tickets = parse(jsonDbPath, defaultTickets);
  return tickets.find((ticket) => ticket.title === title);
}

//CHANGE
export function createTicket(newTicket: NewTicket): Ticket {
  const tickets = parse(jsonDbPath, defaultTickets);
  const lastId = tickets[tickets.length - 1].id;
  const ticket: Ticket = { id: lastId + 1, ...newTicket };
  const updatedTickets = [...tickets, ticket];
  serialize(jsonDbPath, updatedTickets);
  return ticket;
}

//CHANGE
export function deleteTicket(id: number): Ticket | undefined {
  const tickets = parse(jsonDbPath, defaultTickets);
  const index = tickets.findIndex((ticket) => ticket.id === id);
  if (index === -1) return undefined;

  const deletedElements = tickets.splice(index, 1);
  serialize(jsonDbPath, tickets);
  return deletedElements[0];
}

//CHANGE
export function updateTicket(
  id: number,
  updatedTicket: Partial<NewTicket>
): Ticket | undefined {
  const tickets = parse(jsonDbPath, defaultTickets);
  const ticket = tickets.find((ticket) => ticket.id === id);
  if (!ticket) return undefined;

  if (updatedTicket.title !== undefined) {
    ticket.title = updatedTicket.title;
  }
  if (updatedTicket.description !== undefined) {
    ticket.description = updatedTicket.description;
  }

  serialize(jsonDbPath, tickets);
  return ticket;
}