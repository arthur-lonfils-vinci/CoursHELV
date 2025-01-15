
import { useCallback, useEffect, useState } from "react";
import { createTicket } from "../../utils/ticket";
import { NewTicket, Ticket } from "../../types/tickets";
import { getAuthenticatedUser } from "../../utils/session";
import { AuthenticatedUser } from "../../types/users";

export const TicketPage: React.FC = () => {
  const [ticket, setTicket] = useState<NewTicket>();
  const [addedTicket, setAddedTicket] = useState<Ticket>();
  const [isAuthenticatedUser, setIsAuthenticatedUser] = useState(false);
  const [authenticatedUser, setAuthenticatedUser] =
    useState<AuthenticatedUser>();
  const [error, setError] = useState<string>();

  const handleCreationTicket = useCallback(async () => {
    try {
      if (!ticket) {
        console.error("No ticket to create");
        return;
      } else if (!authenticatedUser) {
        console.error("No authenticated user");
        return;
      }
      setError(undefined);
      ticket.creator = authenticatedUser.username;
      ticket.creationDate = new Date().toISOString();
      const response = await createTicket(
        ticket as NewTicket,
        getAuthenticatedUser() as AuthenticatedUser
      );
      setAddedTicket(response as Ticket);
    } catch (error) {
      setError((error as Error).message);
    }
  }, [ticket]);

  useEffect(() => {
    if (getAuthenticatedUser() === undefined) {
      console.log("User not authenticated");
      return;
    } else {
      setAuthenticatedUser(getAuthenticatedUser());
      setIsAuthenticatedUser(true);
    }
  }, [handleCreationTicket]);

  if (!isAuthenticatedUser) {
    return (
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh",
        }}
      >
        <h2>You need to login to create a ticket</h2>
      </div>
    );
  }

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        marginTop: "50px",
      }}
    >
      <h2>Create a Ticket</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleCreationTicket();
        }}
        style={{ display: "flex", flexDirection: "column", width: "300px" }}
      >
        <label>
          Title:
          <input
            type="text"
            value={ticket?.title || ""}
            onChange={(e) =>
              setTicket({ ...ticket, title: e.target.value } as NewTicket)
            }
            required
          />
        </label>
        <label>
          Description:
          <textarea
            value={ticket?.description || ""}
            onChange={(e) =>
              setTicket({ ...ticket, description: e.target.value } as NewTicket)
            }
            required
          />
        </label>
        <button type="submit" style={{ marginTop: "20px" }}>
          Create Ticket
        </button>
      </form>
      {error && <div style={{ color: "red" }}>{error}</div>}
      {!error && addedTicket && (
        <div
          style={{
            marginTop: "20px",
            width: "300px",
            border: "1px solid #ccc",
            padding: "10px",
          }}
        >
          <h3>Ticket created successfully with :</h3>
          <p>
            <strong>ID:</strong> {addedTicket.id}
          </p>
          <p>
            <strong>Title:</strong> {addedTicket.title}
          </p>
          <p>
            <strong>Description:</strong> {addedTicket.description}
          </p>
          <p>
            <strong>Creator:</strong> {addedTicket.creator}
          </p>
          <p>
            <strong>Created at:</strong> {addedTicket.creationDate}
          </p>
        </div>
      )}
    </div>
  );
};
