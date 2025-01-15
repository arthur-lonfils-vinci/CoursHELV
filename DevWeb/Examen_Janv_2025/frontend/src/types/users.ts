
export interface User {
    username: string;
    password: string;
}

export interface AuthenticatedUser {
    username: string;
    token: string;
}

export type MaybeAuthenticatedUser = AuthenticatedUser | undefined;

