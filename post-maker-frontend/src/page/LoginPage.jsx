import React, { useEffect, useRef, useState } from "react";
import { Link, Navigate } from "react-router-dom";
import { loginUser } from "../api/loginUser";


const LoginPage = () => {
  const [loginResponse, setLoginResponse] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const usernameRef = useRef();
  const passwordRef = useRef();

  const handleOnSubmit = async (e) => {
    e.preventDefault();

    const userLogin = {
      username: usernameRef.current.value,
      password: passwordRef.current.value,
    };

    setIsLoading(true);
    try {
      const response = await loginUser(userLogin, {});
      setLoginResponse(response);
    } catch (error) {
      console.error("Error logging in user:", error);
    } finally {
      setIsLoading(false);
    }
  };

  // useEffect(() => {
  //   console.log(loginResponse);
  // }, [loginResponse])
  
  if (loginResponse?.responseCode === "002") {
   localStorage.setItem("username", usernameRef.current.value);
    return <Navigate to={`/${usernameRef.current.value}`} />;
  }

  return (
    <div style={styles.outerContainer}>
      <div style={styles.container}>
        <form onSubmit={handleOnSubmit} style={styles.form}>
          <label style={styles.label}>
            Username
            <input type="text" ref={usernameRef} style={styles.input} />
          </label>
          <label style={styles.label}>
            Password
            <input type="password" ref={passwordRef} style={styles.input} />
          </label>
          <button type="submit" style={styles.button}>
            Login
          </button>
        </form>
        {isLoading && <div style={styles.loader}>Processing...</div>}
        <div style={styles.registerText}>
          Not yet registered? <Link to="/register" style={styles.link}>Click here</Link>
        </div>
      </div>
    </div>
  );
};

const styles = {
  outerContainer: {
    width: "100vw",
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#f5f5f5",
  },
  container: {
    maxWidth: "400px",
    width: "100%",
    padding: "20px",
    border: "1px solid #ccc",
    borderRadius: "10px",
    backgroundColor: "#fff",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.1)",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  label: {
    marginBottom: "10px",
    fontWeight: "bold",
    fontSize: "14px",
  },
  input: {
    padding: "8px",
    fontSize: "16px",
    borderRadius: "4px",
    border: "1px solid #ccc",
    marginBottom: "15px",
    width: "100%",
  },
  button: {
    padding: "10px",
    fontSize: "16px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
  loader: {
    marginTop: "15px",
    fontSize: "16px",
    color: "#007bff",
    textAlign: "center",
  },
  registerText: {
    marginTop: "20px",
    fontSize: "14px",
    textAlign: "center",
  },
  link: {
    color: "#007bff",
    textDecoration: "none",
  },
};

export default LoginPage;
