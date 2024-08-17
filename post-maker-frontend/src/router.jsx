import { createBrowserRouter, Navigate } from "react-router-dom";
import LoginPage from "./page/LoginPage";
import ErrorPage from "./page/ErrorPage";
import NewPostPage from "./page/NewPostPage";
import  { landingPage } from "./page/LandingPage";
import RegistrationPage from "./page/RegistrationPage";

export const router = createBrowserRouter([
  // login page by default
  
    
      {
        index: true,
        element: <LoginPage/>,
        errorElement: <ErrorPage />,
      },

      {
        path: "/register",
        element: <RegistrationPage />,
      },


      {
        path: "/new-post",
        element: <NewPostPage />,
      },

      {
        path: ":username",
        ...landingPage,
      },

  
]);
