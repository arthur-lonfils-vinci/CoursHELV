import { ReactElement } from "react";
import UserCard from "./user_card";

interface UsersProps {
    title: string;
    children: ReactElement<typeof UserCard>[] | ReactElement<typeof UserCard>;
    }

const Users = (props: UsersProps) => {
    return (
        <div className="user" key={props.title}>
            <h2>{props.title}</h2>
            <div className="user-cards">{props.children}</div>
        </div>
    );
}

export default Users;