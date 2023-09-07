import logointro from "../assets/logointro.png";
import "../styles/signIn.css";

export default function SignIn() {
  return (
    <div className="container">
      <form>
        <h1>Sign in</h1>
        <h6 style={{color:"gray"}}>Or use email account</h6>
        <div>
          <input  type="email" id="email" name="email" placeholder="Username" />
        </div>
        <input type="password" id="password" name="password" placeholder="Password" />
        <div>
          <button type="submit">Sign In</button>
        </div>
        <div>
            <h4>New one ?  .... <a href="/signup" target="_blank">Sign Up</a></h4>
         
        </div>
      </form>
      <img src={logointro} alt="logo" className="img" />

    </div>
  );
}
