import "./user_card.css"

interface UserCardProps {
  //index: number;
  user: string;
  age : number;
  isOnline: boolean;
}

const UserCard = (props: UserCardProps) => {
  return (
    <div className="user-card" key={props.user}>
      <h3 className="user-name">{props.user}</h3>
      <p className="user-age">{props.age}</p>
      <p className={props.isOnline ? "user-online" : "user-offline"}>{props.isOnline ? "Online" : "Offline"}</p>
    </div>
  );
};

export default UserCard;