import { createRoot } from 'react-dom/client'
import './index.css'
import App from './components/app/index.tsx'
import HomePage from './pages/main/index.tsx';
import { TicketPage } from './pages/tickets/index.tsx';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "",
        element: <HomePage />,
      },
      {
        path: "ticket/",
        element: <TicketPage />,
      },
    ],
  },
]);

createRoot(document.getElementById('root')!).render(
    <RouterProvider router={router}/>, 
)
