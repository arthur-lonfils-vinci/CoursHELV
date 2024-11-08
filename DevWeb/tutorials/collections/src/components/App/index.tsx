import Header from "../Header/index.tsx";
import Main from "../Main/index.tsx";
import Footer from "../Footer/index.tsx";
import "./App.css";

function App() {
  return (
    <div className="page">
      <Header title="We love Pizza" version={0+1} />
      <Main />
      <Footer />
    </div>
  );
}

export default App;