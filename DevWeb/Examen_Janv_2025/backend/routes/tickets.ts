
import { Router } from "express";
import { createTicket, readAllTickets, readTicketById, deleteTicket, isTitlePresent } from "../services/tickets"; //CHANGE
import { authorize, isAdmin } from "../utils/auths";
import { NewTicket } from "../types";

const router = Router();

router.get("/error", (_req, _res, _next) => {
  throw new Error("This is an error");
});

router.get("/", authorize, isAdmin, (req, res) => {
  if (req.query.order && typeof req.query.order !== "string") {
    return res.sendStatus(400);
  }

  const tickets = readAllTickets(req.query.order);//CHANGE
  return res.json(tickets);//CHANGE
});


router.get("/:id", authorize, (req, res) => {
  const id = Number(req.params.id);
  const ticket = readTicketById(id);//CHANGE
  if (!ticket) return res.sendStatus(404);
  return res.json(ticket);
});


// ROUTE ADD & DELETE IF needed

// Add a new ticket
router.post('/', authorize, (req, res) => {
  const newTicket = req.body as NewTicket;
  if (isTitlePresent(newTicket.title)) {
    return res.status(400).json({ message: 'Title already exists' });
  } else {
    const newTicketAdded = createTicket(newTicket);
    return res.status(201).json(newTicketAdded);
  }
});

// Delete a ticket by id
router.delete('/:id', authorize, isAdmin, (req, res) => {
  const ticketId = parseInt(req.params.id);
  deleteTicket(ticketId);
  res.status(204).send();
});

export default router;
