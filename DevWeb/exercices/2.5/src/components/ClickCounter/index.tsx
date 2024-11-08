import { useState } from "react";

function ClickCounter(): JSX.Element {
  const [count, setCount] = useState(0);
  return (
    <div>
      <button onClick={() => setCount((count) + 1)}>Click me</button>
      <p>You clicked {count} times</p>
    </div>
  );
}

export default ClickCounter;
