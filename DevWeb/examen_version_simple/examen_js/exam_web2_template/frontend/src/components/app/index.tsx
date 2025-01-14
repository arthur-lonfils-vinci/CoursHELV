
import './index.css'
import { Header } from '../layout/header'
import { Footer } from '../layout/footer'
import { Outlet } from 'react-router-dom';

function App() {


  return (
    <>
      <Header />
      <Outlet />
      <Footer />
    </>
  )
}

export default App;
