import Main from "../Main/index";
import Header from "../Header";
import Footer from "../Footer";

const App = () => {

  return (
    <div className="page">
      <Header title="Movies & Cinemas" version={0+1} />
      <Main />
      <Footer />
    </div>
  );
};

export default App;
