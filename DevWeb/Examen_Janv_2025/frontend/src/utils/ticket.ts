import { Ticket, NewTicket } from "../types/tickets";
import { AuthenticatedUser } from "../types/users";


export const createTicket = async (newTicket: NewTicket, authenticatedUser: AuthenticatedUser): Promise<Ticket> => {
  try {
    console.log(newTicket);
    const response = await fetch("/api/tickets", {
      method: "POST",
      headers: {
        Authorization: authenticatedUser.token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newTicket),
    });
    if (!response.ok) {
      const errorData = await response.json();
      if (errorData.message === 'Title already exists') {
        throw new Error('Title already exists');
      }
      throw new Error(errorData.message || 'An error occurred');
    }
    const data = await response.json();
    if (!data) {
      throw new Error("Invalid data");
    }
    return data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};