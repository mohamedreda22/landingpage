import {createBrowserRouter} from "react-router-dom";
import SignUp from "./components/signup";
import SignIn from "./components/signin";

  export const router = createBrowserRouter([
    {
        path: '',

        children: [
            {
                path: "/",
                element: <SignIn />
            },
                  {
                    path: "/signin",
                    element:  <SignIn />
                  },               
                  {
                    path: "/signup",
                    element: <SignUp/>
                  },

        ],}
  ]);