import { AuthenticatedUser, MaybeAuthenticatedUser, User } from "../types/users";
import { clearAuthenticatedUser, storeAuthenticatedUser } from "./session";


export const loginUser = async (user: User, setAuthenticatedUser: (user: MaybeAuthenticatedUser) => void) => {
    try {
      const options = {
        method: "POST",
        body: JSON.stringify(user),
        headers: {
          "Content-Type": "application/json",
        },
      };

      const response = await fetch("/api/auths/login", options);

      if (!response.ok)
        throw new Error(
          `fetch error : ${response.status} : ${response.statusText}`
        );

      const authenticatedUser: AuthenticatedUser = await response.json();
      console.log("authenticatedUser: ", authenticatedUser);

      setAuthenticatedUser(authenticatedUser);
      storeAuthenticatedUser(authenticatedUser);
    } catch (err) {
      console.error("loginUser::error: ", err);
      throw err;
    }
};

export const logoutUser = async () => {
  clearAuthenticatedUser();
}