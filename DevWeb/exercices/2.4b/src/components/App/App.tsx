import './App.css'
import Header from '../Header';
import Main from '../Main';
import Footer from '../Footer';

function App() {

  return (
    <div className='page'>
      <Header title='User Information' />
      <Main />
      <Footer texte='UserCard@Copyright - 2024'/>
    </div>
  );
};

export default App
