import "./App.css";
import Footer from "../Footer";
import Header from "../Header";
import Main from "../Main";
import { useState } from "react";

function App() {
  const [actionToBePerformed, setActionToBePerformed] = useState(false);

  const handleHeaderClick = () => {
    setActionToBePerformed(true);
  };

  const clearActionToBePerformed = () => {
    setActionToBePerformed(false);
  };

  return (
    <div className="page">
      <Header
        title="We love Pizza"
        version={0 + 1}
        handleHeaderClick={handleHeaderClick}
      />
      <Main
        actionToBePerformed={actionToBePerformed}
        clearActionToBePerformed={clearActionToBePerformed}
      />
      <Footer />
    </div>
  );
}

export default App;
