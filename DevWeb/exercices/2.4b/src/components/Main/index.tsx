import "./Main.css";
import Users from "./User/users";
import UserCard from "./User/user_card";

const Main = () => {
  const title = "List of Users";
  const users = [
    {
      name: "John Doe",
      age: 25,
      isOnline: true,
    },
    {
      name: "Jane Doe",
      age: 22,
      isOnline: false,
    },
    {
      name: "James Smith",
      age: 30,
      isOnline: true,
    },
    {
      name: "Judy Smith",
      age: 28,
      isOnline: false,
    },
  ];

  return (
    <main>
      <Users title={title}>
        {users.map((user, index) => (
          <UserCard
            key={index++}
            user={user.name}
            age={user.age}
            isOnline={user.isOnline}
          />
        ))}
      </Users>
    </main>
  );
};

export default Main;
